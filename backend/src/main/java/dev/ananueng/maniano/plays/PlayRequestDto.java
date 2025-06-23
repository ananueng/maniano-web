package dev.ananueng.maniano.plays;

import java.time.LocalDateTime;

public record PlayRequestDto(
        String name,
        Integer songId,
        LocalDateTime dueDate
) {}