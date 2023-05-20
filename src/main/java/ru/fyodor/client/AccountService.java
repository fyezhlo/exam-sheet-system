package ru.fyodor.client;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Optional;

public class AccountService extends Account {

    @Override
    public Account createAccount(byte[] seed) {
        try {
            generateKeyPair(seed);
        } catch (NoSuchAlgorithmException e) {
            //this will never happen
        }
        Arrays.fill(seed, (byte) 0);
        return null;
    }

    @Override
    public Optional<byte[]> pushData(byte[] data) {
        return Optional.empty();
    }

    @Override
    public Optional<byte[]> getData(byte[] id) {
        return Optional.empty();
    }
}
