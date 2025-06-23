package dev.ananueng.maniano.plays;

import dev.ananueng.maniano.file.File;
import dev.ananueng.maniano.file.FileService;
import dev.ananueng.maniano.song.Song;
import dev.ananueng.maniano.song.SongNotFoundException;
import dev.ananueng.maniano.song.SongRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayService {

    private final PlayRepository playRepository;
    private final SongRepository songRepository;
    private final PlayMapper playMapper;
    private final FileService fileService;

    public PlayService(PlayRepository playRepository, SongRepository songRepository, PlayMapper playMapper, FileService fileService) {
        this.playRepository = playRepository;
        this.songRepository = songRepository;
        this.playMapper = playMapper;
        this.fileService = fileService;
    }

    public List<PlayResponseDto> findAll() {
        return playRepository.findAll().stream()
                .map(playMapper::playToResponseDto)
                .collect(Collectors.toList());
    }

    public PlayResponseDto findExistingById(Integer id) {
        Play play = playRepository.findById(id).orElseThrow(PlayNotFoundException::new);
        return playMapper.playToResponseDto(play);
    }

    // public PlayTableTypeSubNavResponseDto findFilesByIdAndType(Integer id, String tableType) {
    //     Play play = playRepository.findById(id).orElseThrow(PlayNotFoundException::new);
    //     return playMapper.playToTableTypeNavResponseDto(play, FileType.valueOf(tableType));
    // }

    public PlayNavDataDto findNavDataById(Integer id) {
        Play play = playRepository.findById(id).orElseThrow(PlayNotFoundException::new);
        return playMapper.playToNavDataDto(play);
    }

    @Transactional
    public void create(PlayRequestDto playRequestDto) {
        Song existingSong = songRepository.findById(playRequestDto.songId())
                .orElseThrow(SongNotFoundException::new);
        Play play = playMapper.requestDtoToPlay(playRequestDto);
        play.setSong(existingSong);
        playRepository.save(play);
    }

    @Transactional
    public void patch(PlayRequestDto playRequestDto, Integer id) {
        Play existingPlay = playRepository.findById(id).orElseThrow(PlayNotFoundException::new);
        if (playRequestDto.songId() != null) {
            Song existingSong = songRepository.findById(playRequestDto.songId())
                    .orElseThrow(SongNotFoundException::new);
            existingPlay.setSong(existingSong);
        }
        if (playRequestDto.name() != null) {
            existingPlay.setName(playRequestDto.name());
        }
        if (playRequestDto.dueDate() != null) {
            existingPlay.setDueDate(playRequestDto.dueDate());
        }
        playRepository.save(existingPlay);
    }

    @Transactional
    public void delete(Integer id) {
        Play existingPlay = playRepository.findById(id).orElseThrow(PlayNotFoundException::new);
        existingPlay.setDeletedAt(LocalDateTime.now());
        existingPlay.setDeleted(true);

        // softly delete
        for (File file : existingPlay.getFiles()) {
            fileService.delete(file.getId());
        }
    }
}