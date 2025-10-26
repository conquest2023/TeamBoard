package project.TeamBoard.application.command.board;

import java.time.Instant;

public record UpdateBoardCommand(

        Long id,
        String name,
        boolean archived,
        Long version,
        Instant updatedAt

){}
