package dev.ananueng.maniano.song;

public record SongRequestDto(
        String title,
        String artist,
        String assetsUrl,
        String coverUrl,
        Integer totalViews,
        Integer totalLikes,
        Integer totalFavorites,
        Boolean isPublic
) {}
