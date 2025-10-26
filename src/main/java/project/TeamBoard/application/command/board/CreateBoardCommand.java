package project.TeamBoard.application.command.board;


import java.time.Instant;

public record CreateBoardCommand(
        String title,

        String email,

        String name,

        boolean archived,

        Instant createdAt,
        Instant updatedAt
) {}
