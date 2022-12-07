package com.tweet.utility;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidation {
	public static List<String> validateRegistration(String firstName, String email,String password, String seq_question) {
		List<String> errors = new ArrayList<>();
		String message;

		if (firstName.equals("")) {
			message = "First Name cannot be empty";
			errors.add(message);
		}
		if (firstName.length() > 30) {
			message = "First Name cannot be more than 30 characters";
			errors.add(message);
		}
// Email
		if (email.equals("")) {
			message = "Email cannot be empty";
			errors.add(message);
		}
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			message = "Email Format [" + email + "] is Invalid. Example Format: [name@example.com]";
			errors.add(message);
		}
		if (email.length() > 50) {
			message = "Email cannot be more than 50 characters";
			errors.add(message);
		}

// Password
		if (password.equals("")) {
			message = "Password cannot be empty";
			errors.add(message);
		}
		if (password.length() > 30) {
			message = "Password cannot be more than 30 characters";
			errors.add(message);
		}
		
// Security Question
		if (seq_question.equals("")) {
			message = "Password cannot be empty";
			errors.add(message);
		}
		if (seq_question.length() > 30) {
			message = "Password cannot be more than 30 characters";
			errors.add(message);
		}
		return errors;
	}
	
public static List<String> validateLoginDetail(String email, String password){
		
		List<String> errors = new ArrayList<>();
		String message;
		
		// Email
				if (email.equals("")) {
					message = "Email cannot be empty";
					errors.add(message);
				}
				String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(email);
				if (!matcher.matches()) {
					message = "Email Format [" + email + "] is Invalid. Example Format: [name@example.com]";
					errors.add(message);
				}
				if (email.length() > 50) {
					message = "Email cannot be more than 50 characters";
					errors.add(message);
				}

		// Password
				if (password.equals("")) {
					message = "Password cannot be empty";
					errors.add(message);
				}
				if (password.length() > 30) {
					message = "Password cannot be more than 30 characters";
					errors.add(message);
				}
				
		
				return errors;		
	}
	
	public static List<String> validateDetail(String email, String password, String newPassword){
		
		List<String> errors = new ArrayList<>();
		String message;
		
		// Email
				if (email.equals("")) {
					message = "Email cannot be empty";
					errors.add(message);
				}
				String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(email);
				if (!matcher.matches()) {
					message = "Email Format [" + email + "] is Invalid. Example Format: [name@example.com]";
					errors.add(message);
				}
				if (email.length() > 50) {
					message = "Email cannot be more than 50 characters";
					errors.add(message);
				}

		// Password
				if (password.equals("")) {
					message = "Password cannot be empty";
					errors.add(message);
				}
				if (password.length() > 30) {
					message = "Password cannot be more than 30 characters";
					errors.add(message);
				}
				
				// Password
				if (newPassword.equals("")) {
					message = "newPassword cannot be empty";
					errors.add(message);
				}
				if (newPassword.length() > 30) {
					message = "newPassword cannot be more than 30 characters";
					errors.add(message);
				}
				return errors;		
	}

}
