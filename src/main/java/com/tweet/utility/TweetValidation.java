package com.tweet.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TweetValidation {

	public static List<String> validateDetails(String email, String subject, String desc) {

		List<String> errors = new ArrayList<>();
		String message;

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

		// subject
		if (subject.equals("")) {
			message = "Subject cannot be empty";
			errors.add(message);
		}
		if (subject.length() > 30) {
			message = "Subject cannot be more than 30 characters";
			errors.add(message);
		}
		if (desc.equals("")) {
			message = "Description cannot be empty";
			errors.add(message);
		}
		if (desc.length() > 12000) {
			message = "Description cannot be more than 12000 characters";
			errors.add(message);
		}
		
		return errors;
	}
}
