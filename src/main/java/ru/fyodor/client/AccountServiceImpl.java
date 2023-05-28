package ru.fyodor.client;

import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Service
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
    public byte[] pushData(byte[] data) {
        return new byte[]{0};
    }

    @Override
    public byte[] getData(byte[] id) {
        return new byte[]{0};
    }
}
