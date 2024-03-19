package Util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseUtilByHikariCP {
	
	 private static DataSource dataSource = null;
	 
	 static {
	        HikariConfig config = new HikariConfig();
	        config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:orcl");
	        config.setUsername("sys as sysdba");
	        config.setPassword("Orcle123");
	        config.setMaximumPoolSize(10); 
	        config.setAutoCommit(false); 
	        dataSource = new HikariDataSource(config);
	    }
	 
	 public static Connection getConnection() throws SQLException {
	        return dataSource.getConnection();
	    }
	 
	 public static void closeConnection(Connection connection) {
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	 }

}
