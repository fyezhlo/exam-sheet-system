package ru.fyodor.client;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Optional;

public class AccountServiceImpl extends AccountService {
    @Override
    public Account createAccount(byte[] seed) {
        KeyPair kp = null;
        try {
            kp = generateKeyPair(seed);
        } catch (NoSuchAlgorithmException e) {
            //this will never happen
            System.out.println(e.getMessage());
        }
        Arrays.fill(seed, (byte) 0);

        return new Account(kp);
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
