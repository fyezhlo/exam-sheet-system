package ru.fyodor.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class HashGenerator {

    private final MessageDigest messageDigest;

    public HashGenerator() {
        try {
            this.messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] generateHash(byte[] input) {
        return messageDigest.digest(input);
    }

    public byte[] calculateHashFromArgs(byte[]... args) {
        byte[] result = new byte[] {0};

        for (byte[] arrOfBytes : args) {
            byte[] temp = new byte[result.length + arrOfBytes.length];

        }

        return result;
    }
}
