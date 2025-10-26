package project.TeamBoard.application.service;

import project.TeamBoard.application.command.board.*;
import project.TeamBoard.domain.board.Board;
import project.TeamBoard.interfaces.dto.BoardDetail;
import project.TeamBoard.interfaces.dto.BoardSummary;

public interface BoardService {

    Board createBoard(CreateBoardCommand cmd);
    void renameBoard(RenameBoardCommand cmd);
    void archiveBoard(SetBoardArchivedCommand cmd);        // archived=true/false
    BoardDetailCommand getBoard(Long id);             // 컬럼 포함 상세


    Long deleteBoard(Long id);

    Board updateBoard(UpdateBoardCommand cmd);


//    List<BoardSummary> listBoardsByProject(ListBoardsQuery query);
//    BoardSummary duplicateBoard(DuplicateBoardCommand cmd); // (선택) 컬럼/옵션 복제
}
