package com.tweet.controller;

import java.util.List;
import java.util.Scanner;

import com.tweet.entity.Tweets;
import com.tweet.entity.User;
import com.tweet.exception.ServiceException;
import com.tweet.service.TweetService;
import com.tweet.serviceimpl.TweetServiceImpl;
import com.tweet.utility.TweetValidation;
import com.tweet.utility.Utility;

public class TweetController {

	private static TweetService tweetService = new TweetServiceImpl();
	static Scanner sc = new Scanner(System.in);

	public static void postTweet(String email) {
		System.out.println("Enter Your Tweet heading");
		String subject = sc.nextLine();
		System.out.println("Enter Your Tweet Description");
		String desc = sc.nextLine();
		List<String> validationErrors = TweetValidation.validateDetails(email, subject, desc);
		if (validationErrors.isEmpty()) {
			Tweets tweet = new Tweets(email, subject, desc);
			try {
				String message = tweetService.postTweet(tweet);
				System.out.println(message);
				Utility.waitForMenuDisplay();
				UserController.showTweetApp(email);
			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		} else {
			System.out.println();
			validationErrors.forEach(error -> System.out.println("ERROR : " + error));
			System.out.println("Please enter valid tweet details");
			Utility.waitForMenuDisplay();
			UserController.showTweetApp(email);
		}
	}

	public static void viewMyTweets(String email) {

		try {
			List<Tweets> details = tweetService.viewMyTweets(email);
			if(!details.isEmpty()) {
				System.out.println("Here Is Your Tweet " + details.get(0).getEmail());
				details.stream().forEach(ele -> System.out.println(ele.getSubject() + " :  " + ele.getDescription()));
				Utility.waitForMenuDisplay();
				UserController.showTweetApp(email);
			} else {
				throw new ServiceException("You don't have any Tweets");
			}	
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 

	}

	public static void viewAllTweets(String email) {
		try {
			List<Tweets> details = tweetService.viewAllTweets();
			if(!details.isEmpty()) {
				details.stream().forEach(ele ->{ 
					System.out.println(ele.getSubject() + " : " + ele.getDescription());});
				Utility.waitForMenuDisplay();
				UserController.showTweetApp(email);
			} else {
				throw new ServiceException("Tweets doesn't Exist");
			}
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
	}

	public static void viewAllUserDetails(String email) {
		try {
			List<User> details = tweetService.viewAllUserDetails();
			if(!details.isEmpty()) {

				details.stream().forEach(ele -> System.out.println("FirstName :  "+ele.getFirst_name() + "        Email : " + ele.getEmail() + "       NickName : " + ele.getQuestion() ));
				Utility.waitForMenuDisplay();
				UserController.showTweetApp(email);
			} else {
				throw new ServiceException("Users doesn't Exist");
			}
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
	}


}
