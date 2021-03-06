package board.dao;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.List;

import board.vo.BoardVO;


public interface BoardDao {
	public int insertList(Connection conn, BoardVO bv)
						throws SQLException;
	
	public int updateList(Connection conn, BoardVO bv)
			throws SQLException;
	
	public int deleteList(Connection conn, int board_no)
			throws SQLException;
	
	public boolean checkList(Connection conn, int board_no)
			throws SQLException;
	
	public List<BoardVO> getAllBoardList(Connection conn)
			throws SQLException;
	public List<BoardVO> getSearchList(BoardVO bv, Connection conn)
			throws SQLException;
}
