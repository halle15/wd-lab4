package music.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;



public class ConnectionPool {
	
	private static ConnectionPool connectionPool;
	DriverManager dm;
	
	private static BasicDataSource dataSource;
	
	private ConnectionPool() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		}
		catch (Exception e){
			e.printStackTrace();
			
		}
		
	}
	
	public static synchronized ConnectionPool getInstance() {
		if (connectionPool == null) {
			connectionPool = new ConnectionPool();
		}
		return connectionPool;
	}
	
	public Connection getConnection()
	{
		try 
		{
		
			
			Connection con;
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/product?useSSL=false", "root", "admin");
			
			return con;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public void freeConnection(Connection connection) {
		try
		{
			System.out.println("Logged off SQL server");
			connection.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	

}
