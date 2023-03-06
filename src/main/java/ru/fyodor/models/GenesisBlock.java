package ru.fyodor.models;

public class GenesisBlock{

    private static Block genesisBlock = null;

    private GenesisBlock(byte[] signature, byte[] data) {
        genesisBlock = new Block(
                new byte[]{0},
                data,
                signature
        );
    }
    public static Block getBlock(byte[] signature, byte[] data) {
        if (genesisBlock == null) {
            new GenesisBlock(signature, data);
        }
        return genesisBlock;
    }

}
