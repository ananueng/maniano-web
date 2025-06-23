package dev.ananueng.maniano.plays;

import dev.ananueng.maniano.file.FileNavResponseDto;

import java.util.List;

public record PlayNavItemDto(
        Integer id,
        String title,
        String path,
        String icon,
        List<FileNavResponseDto> subNavData
) {}
