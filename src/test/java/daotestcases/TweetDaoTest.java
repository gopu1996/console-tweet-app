package daotestcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.tweet.dao.TweetDao;
import com.tweet.entity.Tweets;
import com.tweet.entity.User;
import com.tweet.exception.ConnectionFailedExcetion;
import com.tweet.exception.DaoLayerException;
import com.tweet.utility.DatabaseConnection;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TweetDaoTest {
	private static TweetDao dao = new TweetDao();
	private static DatabaseConnection databaseConnection = new DatabaseConnection();

	@Before
	public void init() {
		Connection conn = null;
		try {
			conn = databaseConnection.getConnection();
			conn.setAutoCommit(false);
		} catch (ConnectionFailedExcetion e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@After
	public void teardown() {
		Connection conn;
		try {
			conn = databaseConnection.getConnection();
			conn.setAutoCommit(true);
			conn.close();
		} catch (ConnectionFailedExcetion e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	 @Test 
	  public void addTweets() { 
     try {
	    Tweets tweets = new Tweets();
      tweets.setEmail("test@gmail.com");
      tweets.setSubject("My blog");
      tweets.setDescription("This is my blog");
      dao.postTweet(tweets); 
	    List<Tweets> tweetDetails = dao.viewTweetbyEmail("test@gmail.com");
	    assertEquals("Password must be equals", "test@gmail.com", tweetDetails.get(0).getEmail());
	    
     }catch (DaoLayerException e) { 
    	 // TODO Auto-generated catch block
	  e.printStackTrace(); }
	  
	  }
	
	 @Test 
	  public void TC1_viewTweetbyEmail() { 
	   try {
		    List<Tweets> tweetDetails = dao.viewTweetbyEmail("test@gmail.com");
		    assertEquals("Password must be equals", "test@gmail.com", tweetDetails.get(0).getEmail());
	   }catch (DaoLayerException e) { 
	       e.printStackTrace(); }
	  }
	 
	 @Test 
	  public void TC2_viewAllTweets() { 
	   try {
		    List<Tweets> tweetDetails = dao.viewAllTweets();
		    assertFalse(tweetDetails.isEmpty());
	   }catch (DaoLayerException e) { 
	       e.printStackTrace(); }
	  }

	 @Test 
	  public void TC3_getAllUserDetails() { 
	   try {
		    List<User> tweetDetails = dao.getAllUserDetails();
		    assertFalse(tweetDetails.isEmpty());
	   }catch (DaoLayerException e) { 
	       e.printStackTrace(); }
	  }
	
}
