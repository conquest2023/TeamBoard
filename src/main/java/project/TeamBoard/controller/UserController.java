package project.TeamBoard.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.TeamBoard.application.service.AuthService;
import project.TeamBoard.interfaces.dto.SignUpDto;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthService userService;

//    private final UserMapper userMapper;


    @PostMapping("/login")
    public ResponseEntity<?> login(){

        return ResponseEntity.ok(200);
    }

    @GetMapping("/")
    public String  test(){
        return "hello";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto sign){

//        userService.signUp()
        return null;
    }
}
