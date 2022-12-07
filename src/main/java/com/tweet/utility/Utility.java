package com.tweet.utility;

public class Utility {
 
    public static void waitForMenuDisplay() {
    	System.out.println("Please wait Menu will display soon .....");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    public static void tweetMenuDisplay(String email) {
		System.out.println();
		System.out.println("Welcome to Tweet App Mr. " + email);
		System.out.println("Please choose the option below");
		
		System.out.println("1 : Post Tweet");
		System.out.println("2 : View my tweets");
		System.out.println("3 : View all tweets");
		System.out.println("4 : View All Users ");
		System.out.println("5 : Reset Password ");
		System.out.println("6 : Logout");
		System.out.println();
    }
    
    public static void menuDisplay() {
        System.out.println();
        System.out.println("Welcome To Tweet App ");
        System.out.println();
        System.out.println("1 : Resgister User");
        System.out.println("2 : Login User");
        System.out.println("3 : Forgot Password");
        System.out.println("4 : Exit");
        System.out.println();
    }

    

}
