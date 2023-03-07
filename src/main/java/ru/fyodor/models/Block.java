package ru.fyodor.models;

import lombok.*;
import ru.fyodor.services.BlockChain;
import ru.fyodor.services.HashGenerator;

import java.time.Instant;

@ToString
@EqualsAndHashCode
@Getter
public class Block {
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

        this.currentHash = HashGenerator.calculateHashFromArgs(
                previousHash,
                data,
                signature,
                instant.toString().getBytes()
        );
    }
}
