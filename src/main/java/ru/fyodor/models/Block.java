package ru.fyodor.models;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
public class Block {
    @Setter
    private byte[] currentHash;
    private final byte[] previousHash;
    private final byte[] signature;
    private final Instant instant;
    private List<Transaction> transactionList;
}
