package dev.ananueng.maniano.song;

import dev.ananueng.maniano.plays.Play;
import dev.ananueng.maniano.plays.PlayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final SongMapper songMapper;
    private final PlayService playService;

    public SongService(SongRepository songRepository, PlayService playService, SongMapper songMapper) {
        this.songRepository = songRepository;
        this.playService = playService;
        this.songMapper = songMapper;
    }

    public List<SongResponseDto> findAll() {
        return songRepository.findAll().stream()
                .map(songMapper::songToResponseDto)
                .collect(Collectors.toList());
    }

    public Song findExistingById(Integer id) {
        return songRepository.findById(id).orElseThrow(SongNotFoundException::new);
    }

    public SongResponseDto findById(Integer id) {
        return songMapper.songToResponseDto(findExistingById(id));
    }

    @Transactional
    public void create(SongRequestDto songRequestDto) {
        Song song = songMapper.requestDtoToSong(songRequestDto);
        songRepository.save(song);
    }

    @Transactional
    public void patch(SongRequestDto songRequestDto, Integer id) {
        Song existingSong = findExistingById(id);
        if (songRequestDto.title() != null) {
            existingSong.setTitle(songRequestDto.title());
        }
        if (songRequestDto.artist() != null) {
            existingSong.setArtist(songRequestDto.artist());
        }
        // don't edit likes/views manually
        if (songRequestDto.isPublic() != null) {
            existingSong.setIsPublic(songRequestDto.isPublic());
        }
        songRepository.save(existingSong);
    }

    @Transactional
    public void delete(Integer id) {
        Song existingSong = findExistingById(id);
        existingSong.setDeletedAt(LocalDateTime.now());

        // soft delete
        existingSong.setDeleted(true);
        for (Play play : existingSong.getPlays()) {
            playService.delete(play.getId());
        }

        // hard delete
        // songRepository.delete(existingSong);
    }

    public List<SongResponseDto> findBySongTitle(String title) {
        return songRepository.findAllByTitle(title).stream()
                .map(songMapper::songToResponseDto)
                .collect(Collectors.toList());
    }
}