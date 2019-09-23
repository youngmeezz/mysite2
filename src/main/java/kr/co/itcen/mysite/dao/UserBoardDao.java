package kr.co.itcen.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.itcen.mysite.vo.UserBoardVo;

public class UserBoardDao {

	
	/////select 게시판 첫 조회하기User랑 Board랑 조인한 부분/////
	public List<UserBoardVo> getList() {
		List<UserBoardVo> result = new ArrayList<UserBoardVo>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = getConnection();

		
			String sql = "select b.no, b.title, u.name, b.hit, b.reg_date \r\n" + 
					"from user u, board b\r\n" + 
					"where u.no = b.user_no and b.status=1\r\n" + 
					"order by b.no desc\r\n" + 
					"limit 0,5;";
			pstmt = connection.prepareStatement(sql);

		
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				int hit = rs.getInt(4);
				String registerDate = rs.getString(5);
//				Long userNo = rs.getLong(6);
//				int status = rs.getInt(7);
				
				UserBoardVo vo = new UserBoardVo();
				
				vo.setNo(no);
				vo.setTitle(title);
				vo.setName(name);
				vo.setHit(hit);
				vo.setRegisterDate(registerDate);
//				vo.setUserNo(userNo);
//				vo.setStatus(status);
				
				result.add(vo);
			}

		} catch (SQLException e) {
			//System.out.println("error:" + e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}


	private Connection getConnection() throws SQLException {
		Connection connection = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mariadb://192.168.1.78:3306/webdb?characterEncoding=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");

		} catch (ClassNotFoundException e) {
			System.out.println("Fail to Loading Driver:" + e);
		}

		return connection;
	}
}