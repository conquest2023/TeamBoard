package project.TeamBoard.application.command;

public record CreateUserCommand(
    String email,
    String username,
    String rawPassword
){}
