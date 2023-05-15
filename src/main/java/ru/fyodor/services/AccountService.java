package ru.fyodor.services;

import ru.fyodor.client.Client;
import ru.fyodor.models.Account;

import java.util.Optional;

public class AccountService extends Account {
    public AccountService(byte[] publicKey, byte[] address) {
        super(publicKey, address);
    }

    @Override
    public Client createAccount(byte[] seedPhrase) {
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

    // creating signature for transact
    private byte[] sign(){
        return null;
    }
}
