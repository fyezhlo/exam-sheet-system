package ru.fyodor.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Transaction {
    private final Token token;
    private final byte[] signature;
    private final byte[] transactionHash;
    private final byte[] prevBlockHash;
    private final byte[] randomBytes;
}
