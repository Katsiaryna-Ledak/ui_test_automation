package com.example.framework.utils;

import java.util.UUID;

public class RandomEmailGenerator {

    private static final String DOMAIN = "test.com";
    private static final int LENGTH = 8;

    public static String generate() {
        return generate(DOMAIN);
    }

    public static String generate(String domain) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String randomPart = uuid.substring(0, LENGTH);
        return "aqa_" + randomPart + "@" + domain;
    }
}