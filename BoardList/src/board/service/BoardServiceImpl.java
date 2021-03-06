package board.service;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBCUtil.JDBCUtil;
import board.dao.BoardDao;
import board.dao.BoardDaoImpl;
import board.vo.BoardVO;



public class BoardServiceImpl implements BoardService {
	private BoardDao boardDao;
	private Connection conn;
	
	public BoardServiceImpl() {
		boardDao = new BoardDaoImpl();
	}
	
	@Override
	public int insertList(BoardVO bv) {
			int cnt = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			cnt = boardDao.insertList(conn, bv);
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, null, null, null);
		}
		
		return cnt;
	}


	@Override
	public int updateList(BoardVO bv) {
			int cnt = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			cnt = boardDao.updateList(conn, bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, null, null, null);
		}
		
		return cnt;
	}

	@Override
	public int deleteList(int board_no) {
int cnt = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			cnt = boardDao.deleteList(conn, board_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, null, null, null);
		}
		
		return cnt;
	}

	@Override
	public boolean checkList(int board_no) {

		boolean chk = false;
		
		try {
			conn = JDBCUtil.getConnection();
			chk = boardDao.checkList(conn, board_no);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, null, null, null);
		}
		
		return chk;
	}

	@Override
	public List<BoardVO> getAllBoardList() {

		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			boardList = boardDao.getAllBoardList(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, null, null, null);
		}
		
		return boardList;
	}

	@Override
	public List<BoardVO> getSearchList(BoardVO bv) {
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			boardList = boardDao.getSearchList(bv, conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, null, null, null);
		}
		return boardList;
	}
}
