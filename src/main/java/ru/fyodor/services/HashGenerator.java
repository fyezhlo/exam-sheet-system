package ru.fyodor.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {

    private final MessageDigest messageDigest;

    public HashGenerator() throws NoSuchAlgorithmException {
        this.messageDigest = MessageDigest.getInstance("SHA-256");
    }

    public byte[] generateHash(String input) {
        return messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
    }
}
