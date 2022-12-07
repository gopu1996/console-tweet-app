package com.tweet.controller;

import java.util.List;
import java.util.Scanner;

import com.tweet.entity.User;
import com.tweet.exception.ServiceException;
import com.tweet.service.UserService;
import com.tweet.serviceimpl.UserServiceImpl;
import com.tweet.utility.UserValidation;
import com.tweet.utility.Utility;

public class UserController {

	private static UserService userService = new UserServiceImpl();
	static Scanner sc = new Scanner(System.in);

	public void registerUser() {

		System.out.println("Enter Your First name");
		String firstName = sc.next();
		System.out.println("Enter Your email");
		String email = sc.next();
		System.out.println("Enter Your password");
		String password = sc.next();
		System.out.println("Enter Your Nick name");
		String secQuestion = sc.next();
		List<String> validationErrors = UserValidation.validateRegistration(firstName, email, password, secQuestion);
		if (validationErrors.isEmpty()) {
		User user = new User(firstName, email, password, secQuestion, false);
			try {
				String message = userService.registerUser(user);
				System.out.println(message);
				Utility.waitForMenuDisplay();
				HomeController.launchApp();
			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		} else {
			  System.out.println();
			validationErrors.forEach(error -> System.out.println("ERROR : " + error));
			System.out.println("Please enter valid registration details");
			Utility.waitForMenuDisplay();
			HomeController.launchApp();
		}
	}

	public void loginUser() {
		System.out.println("Enter Your email");
		String email = sc.next();
		System.out.println("Enter Your password");
		String password = sc.next();
		List<String> validationErrors = UserValidation.validateLoginDetail(email, password);
		if (validationErrors.isEmpty()) {
			try {
				String userDetails = userService.loginUser(email, password);
				System.out.println(userDetails);
				if (userDetails.equals("Login Successfully")) {
					boolean status = userService.getStatusOfUser(email);
					if(status) {
						System.out.println("Your Account is Already Active...");
						Utility.waitForMenuDisplay();
						showTweetApp(email);
					}else {
						System.out.println("We are Activating Account Please wait...");
						boolean active =  userService.updateStatusForUser(true, email);
						if(active) {
							Utility.waitForMenuDisplay();
							showTweetApp(email);
						} else {
							throw new ServiceException("You details are not Active");
						}
					} 
				} else {
					Utility.waitForMenuDisplay();
					HomeController.launchApp();
				}

			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		} else {
			System.out.println();
			validationErrors.forEach(error -> System.out.println("ERROR : " + error));
			System.out.println("Please enter valid Login details");
			Utility.waitForMenuDisplay();
			HomeController.launchApp();
		}

	}

	public void forgotPassword() {
		System.out.println("Please enter your email");
		String email = sc.next();
		System.out.println("Please enter your nick name");
		String nickName = sc.next();
		System.out.println("Enter Your New Password");
		String newPassword = sc.next();
		List<String> validationErrors = UserValidation.validateDetail(email, nickName,newPassword);
		if (validationErrors.isEmpty()) {
		try {
			String message = userService.forgotPassword(nickName, email,newPassword);
			System.out.println(message);
			System.out.println();
			Utility.waitForMenuDisplay();
			HomeController.launchApp();
		} catch (ServiceException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	 }else {
		    System.out.println();
		    validationErrors.forEach(error -> System.out.println("ERROR : " + error));
			System.out.println("Please enter valid details");
			Utility.waitForMenuDisplay();
			HomeController.launchApp();
	 }
	}
	
	private static void resetPassword(String email) {
		System.out.println("Please enter your old password");
		String oldPassword = sc.next();
		System.out.println("Enter Your New Password");
		String newPassword = sc.next();
		List<String> validationErrors = UserValidation.validateDetail(email, oldPassword,newPassword);
		if (validationErrors.isEmpty()) {
			try {
				String message = userService.resetPassword(email,oldPassword,newPassword);
				System.out.println(message);
				System.out.println();
				Utility.waitForMenuDisplay();
				showTweetApp(email);
			} catch (ServiceException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		 }else {
			    System.out.println();
			    validationErrors.forEach(error -> System.out.println("ERROR : " + error));
				System.out.println("Please enter valid details");
				Utility.waitForMenuDisplay();
				showTweetApp(email);
		 }
		
	}
	
	private static void logout(String email) {
		try {
			boolean status = userService.updateStatusForUser(false, email);
			if(!status) {
				Utility.waitForMenuDisplay();
				HomeController.launchApp();
			}else {
				throw new ServiceException("Something went wrong");
			}
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public static void showTweetApp(String email) {
		Utility.tweetMenuDisplay(email);
		int i = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your choice");
		while (i < 3) {
			int choice = sc.nextInt();
			switch (choice) {
			case 1 -> {
				System.out.println();
				TweetController.postTweet(email);
			}
			case 2 -> {
				System.out.println();
				TweetController.viewMyTweets(email);
			}   
			case 3 -> {
				System.out.println();
				TweetController.viewAllTweets(email);
			}
			case 4->{
				System.out.println();
				TweetController.viewAllUserDetails(email);
			}
			case 5->{
				System.out.println();
				UserController.resetPassword(email);
			}
			case 6->{
				System.out.println();
				UserController.logout(email);
			}
			default -> throw new IllegalArgumentException();
			}
		}

	}





}
