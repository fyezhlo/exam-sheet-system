package ru.fyodor.services;

import ru.fyodor.client.AccountService;
import ru.fyodor.generators.HashGenerator;
import ru.fyodor.models.Block;
import ru.fyodor.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BlockChain {

    private final Block genesisBlock;
    private Block lastBlock;

    private List<Block> chain = new ArrayList<>();

    private BlockChain(Block genesisBlock) {
        this.genesisBlock = genesisBlock;
        this.lastBlock = genesisBlock;
        chain.add(genesisBlock);
    }

    public static BlockChain generateBlockChain(AccountService accountService) throws Exception {
        Block genesisBlock = GenesisBlock.getBlock(
                // можно заменить на чтение из конфиг файла
                HashGenerator.getRandomBytes(),
                accountService
        );

        return new BlockChain(genesisBlock);
    }

    void addBlock(Transaction transaction) throws Exception {
        Block newBlock = new Block(
                transaction.getPrevBlockHash(),
                transaction.getTransactionHash(),
                transaction.getAccountService()
                );

        this.lastBlock = newBlock;
        chain.add(newBlock);
    }

    byte[] getLastBlockHash() {
        return lastBlock.getCurrentHash();
    }

    public List<Block> getChain() {
        return this.chain;
    }
}
