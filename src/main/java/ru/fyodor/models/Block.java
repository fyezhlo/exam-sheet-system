package ru.fyodor.models;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;


public class Block {
    private byte[] currentHash;
    private byte[] previousHash;

    private byte[] signature;
    private Instant instant;
    private List<Transaction> transactionList;
}
