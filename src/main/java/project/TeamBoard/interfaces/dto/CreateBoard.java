package project.TeamBoard.interfaces.dto;

import java.time.Instant;
import java.util.List;

public record CreateBoard(


        String title,

        String email,
        String name,
        String description,

        boolean archived,
        Long version,
        Instant createdAt,
        Instant updatedAt,
        List<ColumnSummary> columns
) {
}
