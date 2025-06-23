package dev.ananueng.maniano.file;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class FileMapper {


    public FileMapper() {
    }

    public File requestDtoToFile(FileRequestDto dto) {
        if (dto == null) {
            return null;
        }

        File file = new File();
        file.setName(dto.name());
        file.setFileType(dto.fileType());
        if (dto.fileStatus() == null) {
            file.setFileStatus(FileStatus.WIP);
        }
        else {
            file.setFileStatus(dto.fileStatus());
        }
        return file;
    }

    public FileRequestDto fileToRequestDto(File file) {
        if (file == null) {
            return null;
        }

        return new FileRequestDto(
                file.getName(),
                file.getPlayId(),
                file.getFileType(),
                file.getFileStatus()
        );
    }

    public FileResponseDto fileToResponseDto(File file) {
        if (file == null) {
            return null;
        }
        return new FileResponseDto(
                file.getId(),
                file.getName(),
                file.getPlayId(),
                file.getFileType(),
                file.getFileStatus(),
                file.getCreatedAt(),
                file.getLastModifiedBy(),
                file.getLastModifiedAt()
        );
    }
}