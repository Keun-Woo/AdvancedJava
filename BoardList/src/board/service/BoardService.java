package board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import board.vo.BoardVO;

public interface BoardService {
	public int insertList(BoardVO bv);

public int updateList(BoardVO bv);

public int deleteList(int board_no);

public boolean checkList(int board_no);

public List<BoardVO> getAllBoardList();

public List<BoardVO> getSearchList(BoardVO bv); 
}
