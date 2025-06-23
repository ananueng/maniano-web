package dev.ananueng.maniano.file;

import dev.ananueng.maniano.plays.Play;
import dev.ananueng.maniano.plays.PlayNotFoundException;
import dev.ananueng.maniano.plays.PlayRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {

    private final FileRepository fileRepository;
    private final PlayRepository playRepository;
    private final FileMapper fileMapper;

    public FileService(FileRepository fileRepository, PlayRepository playRepository, FileMapper fileMapper) {
        this.fileRepository = fileRepository;
        this.playRepository = playRepository;
        this.fileMapper = fileMapper;
    }

    public List<FileResponseDto> findAll() {
        return fileRepository.findAll().stream()
                .map(fileMapper::fileToResponseDto)
                .collect(Collectors.toList());
    }

    public FileResponseDto findExistingById(Integer id) {
        File file = fileRepository.findById(id).orElseThrow(FileNotFoundException::new);
        return fileMapper.fileToResponseDto(file);
    }

    @Transactional
    public void create(FileRequestDto fileRequestDto) {
        Play existingPlay = playRepository.findById(fileRequestDto.playId())
                .orElseThrow(PlayNotFoundException::new);
        File file = fileMapper.requestDtoToFile(fileRequestDto);
        file.setPlay(existingPlay);
        fileRepository.save(file);
    }

    @Transactional
    public void patch(FileRequestDto fileRequestDto, Integer id) {
        File existingFile = fileRepository.findById(id).orElseThrow(FileNotFoundException::new);
        if (fileRequestDto.playId() != null) {
            Play existingPlay = playRepository.findById(fileRequestDto.playId())
                    .orElseThrow(PlayNotFoundException::new);
            existingFile.setPlay(existingPlay);
        }
        if (fileRequestDto.name() != null) {
            existingFile.setName(fileRequestDto.name());
        }
        if (fileRequestDto.fileType() != null) {
            existingFile.setFileType(fileRequestDto.fileType());
        }
        if (fileRequestDto.fileStatus() != null) {
            existingFile.setFileStatus(fileRequestDto.fileStatus());
        }
        fileRepository.save(existingFile);
    }

    @Transactional
    public void delete(Integer id) {
        File existingFile = fileRepository.findById(id).orElseThrow(FileNotFoundException::new);
        existingFile.setDeletedAt(LocalDateTime.now());
        existingFile.setDeleted(true);

    }

    public List<FileResponseDto> findAllByType(String type) {
        return fileRepository.findAllByFileType(FileType.valueOf(type)).stream()
                .map(fileMapper::fileToResponseDto)
                .toList();
    }
}