package dev.ananueng.maniano.file;

import java.time.LocalDateTime;
import java.util.List;

public record FileResponseDto(
        Integer id,
        String name,
        Integer playId,
        FileType fileType,
        FileStatus fileStatus,
        LocalDateTime createdAt,
        String lastModifiedBy,
        LocalDateTime lastModifiedAt
) {}