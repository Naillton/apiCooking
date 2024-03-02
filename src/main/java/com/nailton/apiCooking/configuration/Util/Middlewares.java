package com.nailton.apiCooking.configuration.Util;

import java.util.regex.Pattern;

public class Middlewares {

    private static boolean patternMatches(String email) {
        String regexPattern = "^(.+)@(\\S+)$";
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }

    public static boolean validCredentials(String email, String password) {
        boolean isValidEmail = patternMatches(email);
        return isValidEmail && password.length() >= 8;
    }
}
