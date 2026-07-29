package com.privguard.mdm.server.utils;

import java.security.SecureRandom;

public class StringsGenerator {
      
    private static final String CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String random = generateRandomString(60);
        System.out.println(random);
    }
}
