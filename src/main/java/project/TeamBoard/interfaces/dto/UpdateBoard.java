package project.TeamBoard.interfaces.dto;

import java.time.Instant;

public record UpdateBoard(
        Long id,
        String name,
        boolean archived,
        Long version,
        Instant updatedAt
) {}