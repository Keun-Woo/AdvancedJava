package kr.or.ddit.member.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil3;

public class MemberDaoImpl implements IMemberDao {
	
	private Statement stmt;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private static MemberDaoImpl memDao;
	
	private MemberDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static IMemberDao getInstance() {
		if (memDao == null) {
			memDao = new MemberDaoImpl();
					
		}
		return memDao;
	}
	
	@Override
	public int insertMember(Connection conn, MemberVO mv) throws SQLException {
		int cnt;
		try {
		String sql = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr) "
				    + " values (?, ?, ?, ?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, mv.getMemId());
		ps.setString(2, mv.getMemName());
		ps.setString(3, mv.getMemTel());
		ps.setString(4, mv.getMemAddr());
		
		cnt = ps.executeUpdate();
		}finally {
			JDBCUtil3.disConnect(null, null, ps, null);
		}
		return cnt;
	}

	@Override
	public boolean checkMember(Connection conn, String memId) throws SQLException {
		
		boolean chk = false;
		try {
			String sql = "select count(*) as cnt from mymember where mem_id = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, memId);
			
			rs = ps.executeQuery();
			
			int cnt = 0;
			while(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt > 0) {
				chk = true;
			}
			
			
		} finally {
			JDBCUtil3.disConnect(null, null, ps, rs);
		}
		return chk;
	}

	@Override
	public List<MemberVO> getAllMemberList(Connection conn) throws SQLException {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		try {
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("select * from mymember");
			
			while(rs.next()) {
				MemberVO mv = new MemberVO();
				
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				mv.setMemId(memId);
				mv.setMemName(memName);
				mv.setMemTel(memTel);
				mv.setMemAddr(memAddr);
				
				memList.add(mv);
			}
			
		} finally {
			JDBCUtil3.disConnect(null, stmt, null, rs);
		}
		
		return memList;
	}

	@Override
	public int updateMember(Connection conn, MemberVO mv) throws SQLException {
		int cnt;
		try {
			String sql = "update mymember "
					+ " set mem_name = ? "
					+ ", mem_tel = ? "
					+ ", mem_addr = ? "
					+ " where mem_id = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, mv.getMemName());
			ps.setString(2, mv.getMemTel());
			ps.setString(3, mv.getMemAddr());
			ps.setString(4, mv.getMemId());
			
			cnt = ps.executeUpdate();
			
		} finally {
			JDBCUtil3.disConnect(null, null, ps, null);
		}
		
		return cnt;
	}

	@Override
	public int deleteMember (Connection conn, String memId) throws SQLException {
		int cnt;
		try {
			String sql = "delete from mymember where mem_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, memId);
			
			cnt = ps.executeUpdate();
			
		} finally {
			JDBCUtil3.disConnect(null, null, ps, null);
		}
		
		return cnt;
	}

@Override
public List<MemberVO> getSearchMember(MemberVO mv, Connection conn) throws SQLException {
		
	List<MemberVO> memList = new ArrayList<>();
	
	try {
		String sql = "select * from mymember where 1=1 ";
		if(mv.getMemId() !=null && !mv.getMemId().equals("")) {
			sql += " and mem_id = ? ";
		}
		if(mv.getMemName() !=null && !mv.getMemName().equals("")) {
			sql += " and mem_name = ? ";
		}
		if(mv.getMemTel() !=null && !mv.getMemTel().equals("")) {
			sql += " and mem_tel = ? ";
		}
		if(mv.getMemAddr() !=null && !mv.getMemAddr().equals("")) {
			sql += " and mem_addr like '%'||?||'%' ";
		}
		
		ps = conn.prepareStatement(sql);
		int index = 1; 
		if(mv.getMemId() !=null && !mv.getMemId().equals("")) {
			ps.setString(index++, mv.getMemId());
		}
		if(mv.getMemName() !=null && !mv.getMemName().equals("")) {
			ps.setString(index++, mv.getMemName());
		}
		if(mv.getMemTel() !=null && !mv.getMemTel().equals("")) {
			ps.setString(index++,  mv.getMemTel());		
		}
		if(mv.getMemAddr() !=null && !mv.getMemAddr().equals("")) {
			ps.setString(index++,  mv.getMemAddr());		
		}
		
		rs = ps.executeQuery();
		
		while(rs.next()) {
		MemberVO mv2 = new MemberVO();
		mv2.setMemId(rs.getString("mem_id"));
		mv2.setMemName(rs.getString("mem_name"));
		mv2.setMemTel(rs.getString("mem_tel"));
		mv2.setMemAddr(rs.getString("mem_addr"));
			
		memList.add(mv2);
		}
		
		
		
		
	} finally {
		JDBCUtil3.disConnect(null, null, ps, rs);
	}
		
	return memList;
}
	

}
