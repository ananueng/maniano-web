package dev.ananueng.maniano.song;

public record SongRequestDto(
        String title,
        String artist,
        Integer views,
        Integer likes,
        Boolean isPublic
) {}