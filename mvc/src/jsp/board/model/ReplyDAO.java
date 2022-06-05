package jsp.board.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import jsp.util.ConnectionPoolTemplate;
public class ReplyDAO {
	private ConnectionPoolTemplate pool;
	private Connection conn;
	PreparedStatement pstmt = null;
	private static ReplyDAO instance = null;
	private ReplyDAO() {
		pool = ConnectionPoolTemplate.getInstance();
	}
	
	public static ReplyDAO getInstance() {
		if(instance == null) {
			synchronized(ReplyDAO.class) {
				instance = new ReplyDAO();
			}
		}
		return instance;
	}
	public void insertReply(int num, String maker, String content) {
		String sql = "insert into reply values(?,?,?,?,\"seq_reply\".nextval,0,0)";
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
			String pubdate = sdf.format(date);
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, maker);
			pstmt.setString(3, pubdate);
			pstmt.setString(4, content);
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
	
	public List<ReplyVO> getReplys(int num){
		String sql = "select * from reply where \"NUM\"=? and \"ref\"=0 ORDER BY \"order\" desc";
        ResultSet rs = null;
        List<ReplyVO> replyList = new ArrayList<>();
        
        try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs= pstmt.executeQuery(); 
			while(rs.next()) {
				ReplyVO tmp = new ReplyVO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5));
					replyList.add(tmp);
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
		return (replyList.size() == 0) ? null : replyList;
	}
	public void delete(int order) {
		String sql = "delete from reply where \"order\"=?";
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order);
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
	public int selectDeleteOrder(int order) { //부모댓글 삭제하면 자식댓글도 삭제되니 같은order값이 몇개 있는지 찾아야함
		String sql = "select count(*) from reply where \"order\"=?";
		ResultSet rs = null;
		int number=0;
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,order);
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				number = rs.getInt(1);
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
		return number;
	}
	public void deleteRe(int step) {
		String sql = "delete from reply where \"step\" = ?";
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, step);
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
	public List<ReplyVO> reReplys(int num){
		String sql = "select * from reply where (\"NUM\"=? and \"ref\" > 0) order by \"step\" desc";
		ResultSet rs = null;
		List<ReplyVO> ls = new ArrayList<>();
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs= pstmt.executeQuery(); 
			while(rs.next()) {
				ReplyVO tmp = new ReplyVO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getInt(6),
						rs.getInt(7));
				ls.add(tmp);
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
		return (ls.size() == 0) ? null : ls;
	}
	public void insertReReply(int num, String maker, String content,int order,int ref) {
		String sql = "insert into reply values(?,?,?,?, ?, ?, \"seq_reply\".NEXTVAL)";
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
			String pubdate = sdf.format(date);
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, maker);
			pstmt.setString(3, pubdate);
			pstmt.setString(4, content);
			pstmt.setInt(5, order);
			pstmt.setInt(6, ref);
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
