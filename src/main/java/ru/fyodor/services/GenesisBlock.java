package ru.fyodor.services;

import ru.fyodor.client.AccountService;
import ru.fyodor.models.Block;

 class GenesisBlock{

    private static Block genesisBlock = null;

    private GenesisBlock(byte[] data, AccountService accountService) throws Exception {
        genesisBlock = new Block(
                new byte[]{0},
                data,
                accountService
        );
    }
    public static Block getBlock(byte[] data, AccountService accountService) throws Exception {
        if (genesisBlock == null) {
            new GenesisBlock(data, accountService);
        }
        return genesisBlock;
    }

}
