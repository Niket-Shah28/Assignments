package com.aurionpro.model;

import java.util.regex.Pattern;

public class ValidateUserDetails {
		private static final Pattern EMAIL_PATTERN = Pattern.compile(
	        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+.com$"
	    );

	    private static final Pattern PHONE_PATTERN = Pattern.compile(
	        "^[6-9]\\d{9}$"
	    );

	    public static boolean isValid(String name, String phone, String email, String password, String address) {
	        return isNotBlank(name) &&
	               isNotBlank(phone) && isValidPhone(phone) &&
	               isNotBlank(email) && isValidEmail(email) &&
	               isNotBlank(password) &&
	               isNotBlank(address);
	    }

	    private static boolean isNotBlank(String input) {
	        return input != null && !input.trim().isEmpty();
	    }

	    private static boolean isValidPhone(String phone) {
	        return PHONE_PATTERN.matcher(phone).matches();
	    }

	    private static boolean isValidEmail(String email) {
	        return EMAIL_PATTERN.matcher(email).matches();
	    }
}
