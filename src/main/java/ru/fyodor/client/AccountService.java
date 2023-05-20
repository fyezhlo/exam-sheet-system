package ru.fyodor.client;

import java.util.Optional;

public class AccountService extends Account {
    public AccountService(byte[] seedPhrase) {
        super(seedPhrase);
    }

    @Override
    public Account createAccount() {
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
