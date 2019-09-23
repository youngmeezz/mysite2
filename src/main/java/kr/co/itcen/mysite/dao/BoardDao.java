package kr.co.itcen.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.mysite.vo.UserVo;

public class BoardDao {

	/////insert/////

	public Boolean insert(BoardVo boardVo) {
		Boolean result = false;
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();

			String sql2 = "insert into board values(null,?,?,0,now(),1,1,0,?,1)";
			pstmt = connection.prepareStatement(sql2);
			
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContents());
			// session authUser로 하는것을 어떻게 해야할지 모르겠음.****************888888
			pstmt.setLong(3, boardVo.getUserNo());
			
			int count = pstmt.executeUpdate();
			result = (count == 1);

			stmt = connection.createStatement();


		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
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
	
	
	/////select 게시글 제목 클릭해서 View할 내용 조회하기/////
	public BoardVo get(Long no) {
		
		BoardVo vo = null;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//System.out.println(no);
		try {
			connection = getConnection();
				
			String sql = "select title, contents,no,user_no from board where no = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, no);
			//pstmt.setLong(2, userNo);
		
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String title = rs.getString(1);
				String contents = rs.getString(2);
				Long no1 = rs.getLong(3);
				Long userNo1 = rs.getLong(4);
				
				vo = new BoardVo();
				
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setNo(no1);
				vo.setUserNo(userNo1);
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

		return vo;
	}
	
	
	
	/////update 수정하기/////

	public void update (String title, String contents, Long no, Long userNo){

		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			
			connection = getConnection();

			//String sql = "update user set name = ?, password = ?, gender = ? where no = ?";
			String sql = "update board set title =?, contents =? where no = ? and user_no = ?";

			pstmt = connection.prepareStatement(sql);
	
			
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			//pstmt.setString(3, registerDate); 업데이트한 날짜도 필요한지 질문
			pstmt.setLong(3, no);
			pstmt.setLong(4, userNo);
			
			pstmt.executeUpdate();	
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// 자원정리해주기 Connection
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	/////delete 삭제하기 update문으로 하고 title이랑 contents만 바꾸면 되나? /////
	
	public void delete(Long no, Long userNo) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = getConnection();
			
			String sql = "update board set status=0 where no=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, no );
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
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


	public int getCount(String kwd) {
		// TODO Auto-generated method stub
		return 0;
	}


	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}



}