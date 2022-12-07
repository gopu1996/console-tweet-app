package com.tweet.controller;

import java.util.Scanner;

import com.tweet.utility.Utility;

public class HomeController {
	
	private static UserController userController = new UserController();
  
	 public static void main(String[] args) {
		
		 launchApp();
	}
	 
	 @SuppressWarnings("resource")
	public static void launchApp() {
	        Utility.menuDisplay();
		    int i =0;
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter your choice"); 
			while(i<3) {
				int choice = sc.nextInt();
				switch(choice) {
				case 1 ->{
					System.out.println();
					userController.registerUser();
				}
				case 2 ->{
					System.out.println();
					userController.loginUser();
				}
				case 3 ->{
					System.out.println();
					userController.forgotPassword();
				}
				case 4 ->{
					System.out.println("You are Exit from the app, Thank you!!");
					System.exit(0);
				}
				 default -> throw new IllegalArgumentException();
				}
			}
	 }
}
