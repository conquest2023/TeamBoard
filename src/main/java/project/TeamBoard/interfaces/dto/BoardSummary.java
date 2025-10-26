package project.TeamBoard.interfaces.dto;


import java.time.Instant;

public record BoardSummary(
        Long id,
        Long projectId,
        String name,
        boolean archived,
        Long version,
        Instant createdAt,
        Instant updatedAt
) {}
