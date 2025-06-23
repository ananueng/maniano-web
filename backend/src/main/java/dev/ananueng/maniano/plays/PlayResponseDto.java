package dev.ananueng.maniano.plays;

import dev.ananueng.maniano.file.FileResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public record PlayResponseDto(
        Integer id,
        String name,
        Integer songId,
        // songName
        LocalDateTime dueDate,
        LocalDateTime createdAt,
        String lastModifiedBy,
        LocalDateTime lastModifiedAt,
        List<FileResponseDto> files
) {}