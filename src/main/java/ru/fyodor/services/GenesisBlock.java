package ru.fyodor.services;

import ru.fyodor.client.Account;
import ru.fyodor.models.Block;

 class GenesisBlock{

    private static Block genesisBlock = null;

    private GenesisBlock(byte[] data, Account account) throws Exception {
        genesisBlock = new Block(
                new byte[]{0},
                data,
                account
        );
    }
    public static Block getBlock(byte[] data, Account account) {
        if (genesisBlock == null) {
            new GenesisBlock(data, account);
        }
        return genesisBlock;
    }

}
