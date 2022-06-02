package jsp.util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionPoolTemplate {
	private static ConnectionPoolTemplate instance;
	private DataSource ds;
	
	private ConnectionPoolTemplate() {
		try{
	        Context init = (Context) new InitialContext();
	        ds = (DataSource)init.lookup("java:comp/env/jdbc/myOracle");
	    } catch(Exception e){
	        System.out.println("Exception : " + e);
	    }
	}
	
	public static ConnectionPoolTemplate getInstance() {
		synchronized(ConnectionPoolTemplate.class) { //동기화 처리
			if(instance == null) {
				instance = new ConnectionPoolTemplate();
			}
		}
		return instance;
	}
	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}
