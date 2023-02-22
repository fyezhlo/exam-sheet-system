package ru.fyodor.models;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.List;

@ToString
public class Block {
    @Setter
    private byte[] currentHash;
    private final byte[] previousHash;
    private final byte[] signature;
    private final Instant instant;

    public Block(byte[] previousHash, byte[] signature) {
        this.previousHash = previousHash;
        this.signature = signature;
        this.instant = Instant.now();
    }

    private List<Transaction> transactionList;
}
