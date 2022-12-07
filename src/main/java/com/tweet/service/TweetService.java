package com.tweet.service;

import java.util.List;

import com.tweet.entity.Tweets;
import com.tweet.entity.User;
import com.tweet.exception.ServiceException;

public interface TweetService {
	
	String postTweet(Tweets tweet) throws ServiceException;
	List<Tweets> viewMyTweets(String email) throws ServiceException;
	List<Tweets> viewAllTweets() throws ServiceException;
	List<User> viewAllUserDetails() throws ServiceException;

}
