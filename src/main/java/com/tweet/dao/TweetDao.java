package com.tweet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tweet.entity.Tweets;
import com.tweet.entity.User;
import com.tweet.exception.ConnectionFailedExcetion;
import com.tweet.exception.DaoLayerException;
import com.tweet.utility.DatabaseConnection;

public class TweetDao {

	
    private  DatabaseConnection databaseConnection = new DatabaseConnection();
   
    PreparedStatement ps = null;
    ResultSet rs = null;
	Connection connect = null;
	
	
	public String postTweet(Tweets tweet) throws DaoLayerException  {
		
		String querry = "insert into tweets(email_id,sub_tweet,desc_tweet) values (?,?,?);";
	    String message = null;
		try {
		connect = databaseConnection.getConnection();
		ps = connect.prepareStatement(querry);
		ps.setString(1,tweet.getEmail());
		ps.setString(2,tweet.getSubject());
		ps.setString(3,tweet.getDescription());
		
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
	
	public List<Tweets> viewTweetbyEmail(String email) throws DaoLayerException {
		String querry = "select * from tweets where email_id=?";
		List<Tweets> tweet = new ArrayList<>();
		try {
		connect = databaseConnection.getConnection();
		ps = connect.prepareStatement(querry);
		ps.setString(1, email);
		rs = ps.executeQuery();
		
		while(rs.next()) {	
			tweet.add(new Tweets(rs.getString(1), rs.getString(2), rs.getString(3)));
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
		return tweet;
	}
	
	public List<Tweets> viewAllTweets() throws DaoLayerException {
		String querry = "select * from tweets";
		List<Tweets> tweet = new ArrayList<>();
		try {
		connect = databaseConnection.getConnection();
		ps = connect.prepareStatement(querry);
		rs = ps.executeQuery();
		
		while(rs.next()) {	
			tweet.add(new Tweets(rs.getString(1), rs.getString(2), rs.getString(3)));
		}
	    }catch(SQLException | ConnectionFailedExcetion e) {
	    	throw new DaoLayerException("Some error occurs while fetching a data",e);
	    }
		finally {
			try {
				connect.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tweet;
	}
	
	public List<User> getAllUserDetails() throws DaoLayerException {
		String querry = "select * from user";
		List<User> user = new ArrayList<>();
		try {
		connect = databaseConnection.getConnection();
		ps = connect.prepareStatement(querry);
		rs = ps.executeQuery();
		
		while(rs.next()) {	
			 user.add(new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5)));
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
	
}
