package ru.fyodor.services;

import ru.fyodor.services.MerkleTree.MerkleTree;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static byte[] calculateHashFromArgs(byte[]... args) {
        List<byte[]> dataList = new ArrayList<>();
        for (byte[] data : args) {
            dataList.add(data);
        }

        return MerkleTree.generateTree(dataList).getHash();
    }
}
