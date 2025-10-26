package project.TeamBoard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.TeamBoard.interfaces.dto.CreateBoard;

@RestController
public class BoardController {


    @PostMapping("/post")
    public ResponseEntity<?> createBoard(@RequestBody
                                         CreateBoard board){

        return null;
    }

}
