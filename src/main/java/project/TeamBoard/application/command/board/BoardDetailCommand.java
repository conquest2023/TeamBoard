package project.TeamBoard.application.command.board;


import project.TeamBoard.interfaces.dto.ColumnSummary;

import java.time.Instant;
import java.util.List;

public record BoardDetailCommand(
        Long id,
        Long projectId,

        String name,

        String email,
        boolean archived,
        Long version,
        Instant createdAt,
        Instant updatedAt
//        List<ColumnSummary> columns
) {}
