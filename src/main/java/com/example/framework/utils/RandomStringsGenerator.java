package com.example.framework.utils;

import java.util.Random;

public class RandomStringsGenerator {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int LENGTH = 5;
    private static final Random RANDOM = new Random();

    public static String generateFirstName() {
        return "Name" + generateRandomString(LENGTH);
    }

    public static String generateLastName() {
        return "Last" + generateRandomString(LENGTH);
    }

    public static String generateMiddleName() {
        return "Mid" + generateRandomString(LENGTH);
    }

    public static String generateNickname() {
        return "Nick" + generateRandomString(LENGTH);
    }

    public static String generateTestComment() {
        return "Comment" + generateRandomString(LENGTH);
    }

    public static String generateCompanyName() {
        return "Company" + generateRandomString(LENGTH);
    }

    public static String generateJobTitle() {
        return "IT-" + generateRandomString(LENGTH);
    }

    public static String generateJobRole() {
        return "Test-" + generateRandomString(LENGTH);
    }

    private static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(ALPHABET.length());
            sb.append(ALPHABET.charAt(index));
        }
        return sb.toString();
    }

    public static String generateCyprusPhoneNumber() {
        StringBuilder sb = new StringBuilder("+357 ");

        sb.append("9");
        sb.append(RANDOM.nextInt(10));
        sb.append(" ");
        for (int i = 0; i < 6; i++) {
            sb.append(RANDOM.nextInt(10));
        }

        return sb.toString();
    }
}
