package dev.ananueng.maniano.song;

import dev.ananueng.maniano.plays.PlayResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public record SongResponseDto(
        Integer id,
        String title,
        String artist,
        Integer views,
        Integer likes,
        Boolean isPublic,
        String createdBy,
        LocalDateTime createdAt,
        String lastModifiedBy,
        LocalDateTime lastModifiedAt,
        String path,
        List<PlayResponseDto> plays
) {}