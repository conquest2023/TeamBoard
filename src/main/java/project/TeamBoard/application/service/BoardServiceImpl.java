package project.TeamBoard.application.service;

import org.springframework.stereotype.Service;
import project.TeamBoard.application.command.board.*;
import project.TeamBoard.domain.board.Board;
import project.TeamBoard.infrastructure.jpa.BoardRepository;
import project.TeamBoard.interfaces.dto.BoardDetail;
import project.TeamBoard.interfaces.dto.BoardSummary;
import project.TeamBoard.interfaces.mapper.BoardCommandMapper;
import project.TeamBoard.interfaces.mapper.BoardDomainToCommandMapper;

import java.time.Instant;

@Service
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    private final BoardDomainToCommandMapper mapper;

    public BoardServiceImpl(BoardRepository boardRepository ,BoardDomainToCommandMapper mapper) {

        this.boardRepository = boardRepository;
        this.mapper=mapper;
    }

    @Override
    public Board createBoard(CreateBoardCommand cmd) {
       return boardRepository.save(
                Board.builder()
                        .name(cmd.name())
                        .title(cmd.title())
                        .email(cmd.email())
                        .createdAt(Instant.now())
                        .archived(true)
                        .build());
    }

    @Override
    public void renameBoard(RenameBoardCommand cmd) {

    }

    @Override
    public void archiveBoard(SetBoardArchivedCommand cmd) {

    }

    @Override
    public BoardDetailCommand getBoard(Long id) {
        Board boardDetail = boardRepository.findBoardDetail(id);
        return mapper.toDetailCommand(boardDetail);
    }

    @Override
    public Long deleteBoard(Long id) {
//        if (boardRepository.)
        return boardRepository.deleteBoard(id);
    }

    @Override
    public Board updateBoard(UpdateBoardCommand cmd) {


        return null;
    }
}
