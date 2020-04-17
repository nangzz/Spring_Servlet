package jdbc.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.user.vo.UserVO;

// 이 클래스에 DB 연결시키는 코드 있음
public class UserDAO {
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe"; // 변경 127.0.0.1
	String user = "scott"; // 변경
	String pass = "tiger"; // 변경

	public UserDAO() {
		//1. Driver class loading
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Driver loading OK!!");
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}		
	}
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, pass);
	}
	
	public void close(Statement stmt, Connection con) throws SQLException {
		if (stmt != null) stmt.close();
		if (con != null) con.close();
	}
	
	//update 하는 메소드
	public int updateUser(UserVO user) {
		String sql = "update users set name = ?, gender = ?, city = ? where userid = ?";
		
		// DB 연결 코드
		Connection con = null;
		PreparedStatement stmt = null;
		int updateCnt = 0;
		try {
			con = getConnection();
			//auto commit 해제 - 직접 컨트롤하기 위해서는 해제해야 함
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sql);
			stmt.setString(1, user.getName());
			stmt.setString(2, Character.toString(user.getGender()));
			stmt.setString(3, user.getCity());
			stmt.setString(4, user.getUserid());
			updateCnt = stmt.executeUpdate();
			// 커밋 - 직접 컨트롤 위해
			// 원래는 커밋 안해줘도 JDBC는 AutoCommit 됨!
			con.commit();
		}catch(SQLException e) {
			//롤백
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				close(stmt,con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return updateCnt;
	}
	
	//userid를 입력받아서 1개의 row를 반환하는 메소드 - 반환 하나니까 굳이 List 타입일 필요 없음
	public UserVO getUser(String userid) {
		String sql = "select * from users where userid = ?";
		Connection con = null;
		PreparedStatement stmt = null;
		UserVO user = null;
		try {
			con = getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, userid);
			ResultSet rs =  stmt.executeQuery();
			if(rs.next()) {
				user = new UserVO(rs.getString("userid"), 
						          rs.getString("name"), 
						          rs.getString("gender").charAt(0), 
						          rs.getString("city"));
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(stmt,con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}
	//전체 row를 반환하는 메소드 - 반환 여러개니까 List<UserVO>로 타입 설정해줌
	public List<UserVO> getUsers() {
		String sql = "select * from users order by userid";
		Connection con = null;
		PreparedStatement stmt = null;
		UserVO user = null;
		List<UserVO> userList = new ArrayList<>();
		try {
			con = getConnection();
			stmt = con.prepareStatement(sql);
			ResultSet rs =  stmt.executeQuery();
			while(rs.next()) {
				user = new UserVO(rs.getString("userid"), 
						          rs.getString("name"), 
						          rs.getString("gender").charAt(0), 
						          rs.getString("city"));
				userList.add(user);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(stmt,con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userList;
	}//getUserList
	
	// insert 하는 메소드
		public int insertUser(UserVO user) {
			String sql = "insert into users values(?, ?, ?, ?)";
			
			// DB 연결 코드
			Connection con = null;
			PreparedStatement stmt = null;
			int insertCnt = 0;
			try {
				con = getConnection();
				//auto commit 해제 - 직접 컨트롤하기 위해서는 해제해야 함
				con.setAutoCommit(false);
				stmt = con.prepareStatement(sql);
				stmt.setString(1, user.getUserid());
				stmt.setString(2, user.getName());
				stmt.setString(3, Character.toString(user.getGender()));
				stmt.setString(4, user.getCity());
				insertCnt = stmt.executeUpdate();
				// 커밋 - 직접 컨트롤 위해
				// 원래는 커밋 안해줘도 JDBC는 AutoCommit 됨!
				con.commit();
			}catch(SQLException e) {
				//롤백
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}finally {
				try {
					close(stmt,con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return insertCnt;
		}
	
	
}
