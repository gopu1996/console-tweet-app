package com.tweet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tweet.entity.User;
import com.tweet.exception.ConnectionFailedExcetion;
import com.tweet.exception.DaoLayerException;
import com.tweet.utility.DatabaseConnection;

public class UserDao {
  
	private  DatabaseConnection databaseConnection = new DatabaseConnection();
	  
	  PreparedStatement ps = null;
	  ResultSet rs = null;
	  Connection connect = null;
	


	public String registerUserInDatabase(User user) throws DaoLayerException  {
		
		String querry = "insert into user(first_name,email,user_password,security_ques,user_status) values (?,?,?,?,?);";
	    String message = null;
		try {
		connect = databaseConnection.getConnection();
		ps = connect.prepareStatement(querry);
		ps.setString(1,user.getFirst_name());
		ps.setString(2,user.getEmail());
		ps.setString(3,user.getPassword());
		ps.setString(4,user.getQuestion());
		ps.setBoolean(5,user.isStatus());
	    int code = ps.executeUpdate();
		    if(code !=0) {
		      message = "Data inserted suceesfully";
		    }
	    }catch(SQLException | ConnectionFailedExcetion e) {
	    	throw new DaoLayerException("Error Occurs inseting a data from Data Base",e);
	    }
		finally {
			try {
				connect.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return message;
		
	}
	
	
	public User getUserDetailsbyEmail(String email) throws DaoLayerException {
		String querry = "select * from user where email=?";
		User user = null;
		try {
		connect = databaseConnection.getConnection();
		ps = connect.prepareStatement(querry);
		ps.setString(1, email);
		rs = ps.executeQuery();
		
		while(rs.next()) {	
			 user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5));
		}
	    }catch(SQLException | ConnectionFailedExcetion e) {
	    	throw new DaoLayerException("Email Id didn't exsist",e);
	    }
		finally {
			try {
				connect.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	public void setUserStatus(boolean status, String email) throws DaoLayerException {
		
		String querry = "update user set user_status=? where email=?";		
		try {
		connect = databaseConnection.getConnection();
		ps = connect.prepareStatement(querry);
		ps.setBoolean(1,status);
		ps.setString(2,email);
		 int code = ps.executeUpdate();
		    if(code !=0) {
		     System.out.println("Status Updated suceesfully");
		    }
	    }catch(SQLException | ConnectionFailedExcetion e) {
	    	throw new DaoLayerException("Error Occurs Updating status into Data Base",e);
	    }
		finally {
			try {
				connect.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		
	}


	public String setPasswordForUser(String password, String email) throws DaoLayerException {
		String querry = "update user set user_password=? where email=?";
		String message = null;
		
		try {
		connect = databaseConnection.getConnection();
		ps = connect.prepareStatement(querry);
		ps.setString(1,password);
		ps.setString(2,email);
		 int code = ps.executeUpdate();
		    if(code !=0) {
		    message = "Password change suceesfully";
		    }
	    }catch(SQLException | ConnectionFailedExcetion e) {
	    	throw new DaoLayerException("Error Occurs Updating status into Data Base",e);
	    }
		finally {
			try {
				connect.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return message;
	
	}
	
	
	
}
