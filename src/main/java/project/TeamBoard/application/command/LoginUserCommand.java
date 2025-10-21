package project.TeamBoard.application.command;

public record LoginUserCommand(

        String email,
        String username,
        String rawPassword
) {
}
