package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 
 * db.properties 파일의 내용으로 DB정보를 설정하는 방법
 * 방법1)Properties객체 이용하기
 *
 */
public class JDBCUtil2 {
	static Properties prop; //Properties객체변수 선언
	
	static {
		prop = new Properties(); //객체 생성
	
		File file = new File("res/db.properties");
		
		try {
			FileInputStream fis = new FileInputStream(file);
			prop.load(fis);
			
			Class.forName(prop.getProperty("driver"));
			
		}catch (IOException e) {
			System.out.println("파일이 없거나 입출력 오류입니다.");
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!!");
			e.printStackTrace();
		}
	}
	/**
	 * 커넥션객체 가져오기
	 */
	public static Connection getConnetction(){
		try {
			return DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("user"),
					prop.getProperty("pass"));
		} catch (SQLException e) {
			System.out.println("DB연결 실패!!!");
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
