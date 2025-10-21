package project.TeamBoard.application.service;

import project.TeamBoard.application.command.LoginUserCommand;
import project.TeamBoard.domain.user.User;
import project.TeamBoard.application.command.CreateUserCommand;

public interface UserService {



    User signUp(CreateUserCommand create);


    User login(LoginUserCommand login);


}
