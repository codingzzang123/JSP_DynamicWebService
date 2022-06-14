package jsp.user.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jsp.util.ConnectionPoolTemplate;
public class UserDAO {
	private ConnectionPoolTemplate pool;
	private Connection conn;
	PreparedStatement pstmt = null;
	private static UserDAO instance = null;
	private UserDAO() {
		pool = ConnectionPoolTemplate.getInstance();
	}
	
	public static UserDAO getInstance() {
		if(instance == null) {
			synchronized(UserDAO.class) {
				instance = new UserDAO();
			}
		}
		return instance;
	}
	
	public void insert(String id, String name, String pw , String age ,String comment) {
		String sql = "insert into \"user\"(\"id\",\"name\",\"pw\",\"age\",\"comment\") values(?,?,?,?,?)";
		
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, id);
			pstmt.setNString(2, name);
			pstmt.setNString(3, pw);
			pstmt.setNString(4, age);
			pstmt.setNString(5, comment);
			pstmt.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {		
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void insert(String id, String name, String pw , String age ,String comment, String img, String code) {
		String sql = "insert into \"user\" values(?,?,?,?,?,?,?)";
		
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, id);
			pstmt.setNString(2, name);
			pstmt.setNString(3, pw);
			pstmt.setNString(4, age);
			pstmt.setNString(5, comment);
			pstmt.setNString(6, img);
			pstmt.setNString(7, code);
			pstmt.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {		
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public boolean idCheck(String id) {
		ResultSet rs = null;
		String sql = "select * from \"user\" where \"id\" = ?";
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(!rs.next()){ //중복 x
				return false;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally {		
			try {
				if(pstmt != null)pstmt.close();
				if(rs != null)rs.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	public boolean isMember(String id, String pw) {
		ResultSet rs = null;
		String sql = "select * from \"user\" where \"id\" = ? and \"pw\" = ?";
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			if(!rs.next()){ //false 는 로그인  실패..
				return false;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally {		
			try {
				if(pstmt != null)pstmt.close();
				if(rs != null)rs.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	public String getPicCode(String id) {
		String sql = "select \"code\" from \"user\" where \"id\" = ?";
		ResultSet rs = null;
		String get = "";
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery(); //쿼리 전송!
			while(rs.next()) {
				get = rs.getString(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {		
			try {
				if(pstmt != null)pstmt.close();
				if(rs != null)rs.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return get;
	}
	public UserVO select(String id) {
		String sql = "select * from \"user\" where \"id\"=?";
		ResultSet rs = null;
		UserVO ls = new UserVO();
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				ls.setId(rs.getString(1));
				ls.setName(rs.getString(2));
				ls.setPw(rs.getString(3));
				ls.setAge(rs.getString(4));
				ls.setComment(rs.getString(5));
				ls.setImg(rs.getString(6));
				ls.setCode(rs.getString(7));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {		
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ls == null?  null : ls;
	}
	public void updateUser(String id, String name, String age, String comment) {
		String sql = "update \"user\" set \"name\"=?, \"age\"=?, \"comment\"=? where \"id\"=?"; 
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,name);
			pstmt.setString(2,age);
			pstmt.setString(3,comment);
			pstmt.setString(4,id);
			pstmt.executeQuery(); 
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {		
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void updateUserPic(String id, String name, String age, String comment,String img, String code) {
		String sql = "update \"user\" SET \"name\"=?, \"age\"=?,\"comment\"=?, \"img\"=?, \"code\"=? WHERE \"id\" = ?"; 
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,name);
			pstmt.setString(2,age);
			pstmt.setString(3,comment);
			pstmt.setString(4,img);
			pstmt.setString(5,code);
			pstmt.setString(6,id);
			pstmt.executeQuery(); 
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {		
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
