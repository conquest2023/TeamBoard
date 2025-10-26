package project.TeamBoard.interfaces.dto;

import java.time.Instant;

public record ColumnSummary(
        Long id,
        Long boardId,
        String name,
        Integer wipLimit,
        Integer orderIdx,
        Instant createdAt,
        Instant updatedAt
) {}