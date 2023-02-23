package ru.fyodor.models;

import lombok.*;

import java.time.Instant;

@ToString
@EqualsAndHashCode
@Getter
public class Block {
    @Setter
    private byte[] currentHash;
    private final byte[] previousHash;
    private final byte[] data;
    private final byte[] signature;
    private final Instant instant;

    public Block(byte[] previousHash, byte[] data, byte[] signature) {
        this.previousHash = previousHash;
        this.data = data;
        this.signature = signature;
        this.instant = Instant.now();
    }
}
