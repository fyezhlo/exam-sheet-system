package ru.fyodor.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {

    private final MessageDigest messageDigest;

    public HashGenerator() throws NoSuchAlgorithmException {
        this.messageDigest = MessageDigest.getInstance("SHA-256");
    }

    public byte[] generateHash(byte[] input) {
        return messageDigest.digest(input);
    }
}
