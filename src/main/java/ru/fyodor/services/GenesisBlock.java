package ru.fyodor.services;

import ru.fyodor.models.Block;

 class GenesisBlock{

    private static Block genesisBlock = null;

    private GenesisBlock(byte[] data) {
        genesisBlock = new Block(
                new byte[]{0},
                data,
                new byte[]{0}
        );
    }
    public static Block getBlock(byte[] data) {
        if (genesisBlock == null) {
            new GenesisBlock(data);
        }
        return genesisBlock;
    }

}
