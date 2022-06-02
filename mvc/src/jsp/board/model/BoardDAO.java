package jsp.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import jsp.util.ConnectionPoolTemplate;

public class BoardDAO {
	private ConnectionPoolTemplate pool;
	private Connection conn;
	PreparedStatement pstmt = null;
	private static BoardDAO instance = null;
	private BoardDAO() {
		pool = ConnectionPoolTemplate.getInstance();
	}
	
	public static BoardDAO getInstance() {
		if(instance == null) {
			synchronized(BoardDAO.class) {
				instance = new BoardDAO();
			}
		}
		return instance;
	}
	
}
