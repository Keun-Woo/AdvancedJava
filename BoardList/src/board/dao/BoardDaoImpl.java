package board.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JDBCUtil.JDBCUtil;
import board.vo.BoardVO;


public class BoardDaoImpl implements BoardDao{
	private Statement stmt;
	private PreparedStatement ps;
	private ResultSet rs;
	
	@Override
	public int insertList(Connection conn, BoardVO bv)throws SQLException {
		
		try {
			String sql = "insert into jdbc_board (board_no, board_title, "
					+ "board_writer, board_date, board_content)"
					+ "values (board_seq.nextVal, ? , ? , sysdate , ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, bv.getBoardtitle());
			ps.setString(2, bv.getBoardwriter());
			ps.setString(3, bv.getBoardcontent());
			
			int cnt = ps.executeUpdate();
			return cnt;
			
		} finally {
			JDBCUtil.disConnect(null, null, ps, null);
		}
		
	}

	@Override
	public int updateList(Connection conn, BoardVO bv) throws SQLException {
		int cnt;
		try {
			String sql = "update jdbc_board "
					+ " set board_title = ? "
					+ " , board_writer = ? "
					+ " , board_date = sysdate "
					+ " , board_content = ? "
					+ " where board_no = ? ";
					
			ps = conn.prepareStatement(sql);
			ps.setString(1, bv.getBoardtitle());
			ps.setString(2, bv.getBoardwriter());
			ps.setString(3, bv.getBoardcontent());
			ps.setInt(4, bv.getBoardno());		
			
			cnt = ps.executeUpdate();
			
			
		} finally {
			JDBCUtil.disConnect(null, null, ps, null);
		}
		
		return cnt;
	}

	@Override
	public int deleteList(Connection conn, int board_no) throws SQLException {
		int cnt;
		try {
			String sql = "delete from jdbc_board where board_no = ? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board_no);
			
			cnt = ps.executeUpdate();
		} finally {
			JDBCUtil.disConnect(null, null, ps, null);
		}
			
		return cnt;
	}

	@Override
	public boolean checkList(Connection conn, int board_no) throws SQLException {
		boolean chk = false;
		try {
			String sql = "select count(*) as cnt from jdbc_board where board_no = ? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board_no);
			
			rs = ps.executeQuery();
			
			int cnt = 0;
			while(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt > 0) {
				chk = true;
			}
			
			
		} finally {
			JDBCUtil.disConnect(null, null, ps, rs);
		}
		return chk;
	}

	@Override
	public List<BoardVO> getAllBoardList(Connection conn) throws SQLException {
		List<BoardVO> memList = new ArrayList<BoardVO>();
		try {
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("select * from jdbc_board");
			
			while(rs.next()) {
				BoardVO bv = new BoardVO();
				
				int boardno = rs.getInt("board_no");
				String boardtitle = rs.getString("board_title");
				String boardwriter = rs.getString("board_writer");
				String boarddate = rs.getString("board_date");
				String boardcontent = rs.getString("board_content");
				
				bv.setBoardno(boardno);
				bv.setBoardtitle(boardtitle);
				bv.setBoardwriter(boardwriter);
				bv.setBoarddate(boarddate);
				bv.setBoardcontent(boardcontent);
				memList.add(bv);
			}
			
		} finally {
			JDBCUtil.disConnect(null, stmt, null, rs);
		}
		
		return memList;
	}

	@Override
	public List<BoardVO> getSearchList(BoardVO bv, Connection conn) throws SQLException {
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			String sql = "select * from jdbc_board where 1=1 ";
			if(bv.getBoardno() != 0) {
				sql += " and board_no = ? ";
			}
			
			if(bv.getBoardtitle() !=null && !bv.getBoardtitle().equals("")) {
				sql += " and board_title like '%'||?||'%' ";
			}
			if(bv.getBoardwriter() !=null && !bv.getBoardwriter().equals("")) {
				sql += " and board_writer like '%'||?||'%' ";
			}

			if(bv.getBoardcontent() !=null && !bv.getBoardcontent().equals("")) {
				sql += " and board_content like '%'||?||'%' ";
			}
	
			ps = conn.prepareStatement(sql);
			int index = 1; 
			if(bv.getBoardno() !=0) {
				ps.setInt(index++, bv.getBoardno());
			}
			if(bv.getBoardtitle() !=null && !bv.getBoardtitle().equals("")) {
				ps.setString(index++, bv.getBoardtitle());
			}
			if(bv.getBoardwriter() !=null && !bv.getBoardwriter().equals("")) {
				ps.setString(index++, bv.getBoardwriter());
			}
			
			if(bv.getBoardcontent() !=null && !bv.getBoardcontent().equals("")) {
				ps.setString(index++, bv.getBoardcontent());
			}
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardVO bv2 = new BoardVO();
			bv2.setBoardno(rs.getInt("board_no"));
			bv2.setBoardtitle(rs.getString("board_title"));
			bv2.setBoardwriter(rs.getString("board_writer"));
			bv2.setBoarddate(rs.getString("board_date"));
			bv2.setBoardcontent(rs.getString("board_content"));
			boardList.add(bv2);
			}
			
		
			
			
		} finally {
			JDBCUtil.disConnect(null, null, ps, rs);
		}
			
		return boardList;
	}
	
}
