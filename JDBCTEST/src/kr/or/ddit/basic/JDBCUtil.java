package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * JDBC드라이버를 로딩하고 Connection 객체를 생성하는 메서드로 구성된 클래스
 *
 */
public class JDBCUtil {
	static {
		//1.드라이버 로딩
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}catch(ClassNotFoundException e){
		System.out.println("드라이버 로딩 실패!!!");
		e.printStackTrace();
	}
	}
	
	public static Connection  getConnetction() {
		try {
			return DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"pc05",
					"java");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}

	public static void disConnect(Connection conn, Statement stmt, 
								PreparedStatement ps,
								ResultSet rs) {
		if(rs != null) try { rs.close(); } catch(SQLException ex) {}
		if(stmt != null) try { stmt.close(); } catch(SQLException ex) {}
		if(ps != null) try { ps.close(); } catch(SQLException ex) {}
		if(conn != null) try { conn.close(); } catch(SQLException ex) {}
	}
}
	

