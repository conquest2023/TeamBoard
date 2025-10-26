package project.TeamBoard.interfaces.dto;


import java.time.Instant;
import java.util.List;

public record BoardDetail(
        Long id,
        Long projectId,
        String name,

        String email,
        boolean archived,
        Long version,
        Instant createdAt,
        Instant updatedAt,
        List<ColumnSummary> columns
) {}