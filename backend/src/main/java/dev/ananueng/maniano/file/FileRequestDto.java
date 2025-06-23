package dev.ananueng.maniano.file;

public record FileRequestDto(
        String name,
        Integer playId,
        FileType fileType,
        FileStatus fileStatus
) {}