package ru.fyodor.models;

import lombok.Getter;

@Getter
public class Transaction {
    private Token token;
    private byte[] signature;
    private byte[] transactionHash;
    private byte[] prevBlockHash;
    private byte[] randomBytes;
}
