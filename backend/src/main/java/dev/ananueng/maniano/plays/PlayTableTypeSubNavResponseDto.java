package dev.ananueng.maniano.plays;

import dev.ananueng.maniano.file.FileNavResponseDto;

import java.util.List;

public record PlayTableTypeSubNavResponseDto(
        List<FileNavResponseDto> subNavData
) {}