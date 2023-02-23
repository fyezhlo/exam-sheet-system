package ru.fyodor.models;

import java.time.Instant;

public class GenesisBlock{

    private static Block genesisBlock = null;

    private GenesisBlock(byte[] signature) {
        genesisBlock = new Block(
                new byte[]{0},
                signature
        );
    }
    public static Block getBlock(byte[] signature) {
        if (genesisBlock == null) {
            new GenesisBlock(signature);
        }
        return genesisBlock;
    }

}
