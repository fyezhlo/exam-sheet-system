package ru.fyodor.models;

import lombok.RequiredArgsConstructor;
import lombok.Setter;


public class Block {
    private final byte[] currentHash;
    private final byte[] previousHash;

    public Block(byte[] currentHash, byte[] previousHash) {
        this.currentHash = currentHash;
        this.previousHash = previousHash;
    }


}
