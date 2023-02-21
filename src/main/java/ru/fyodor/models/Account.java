package ru.fyodor.models;

import lombok.Getter;

@Getter
public class Account {
    private byte[] publicKey;
    private byte[] address;
}
