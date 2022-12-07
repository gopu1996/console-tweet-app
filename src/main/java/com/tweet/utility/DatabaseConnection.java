package com.tweet.utility;

import java.sql.Connection;
import java.sql.DriverManager;


import com.tweet.exception.ConnectionFailedExcetion;

public class DatabaseConnection {
	
	private static String url = "jdbc:mysql://localhost:3306/tweet_app";
	private static String username = "root";
	private static String password = "Localhost@1996";  

	public Connection getConnection() throws ConnectionFailedExcetion {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			throw new ConnectionFailedExcetion("Some things wrong while Establish Database connection",e);
		}
		return con;
	}

}
