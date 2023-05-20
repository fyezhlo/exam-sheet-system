package ru.fyodor.services;

import ru.fyodor.client.Account;
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

    public static BlockChain generateBlockChain(Account account) {
        Block genesisBlock = GenesisBlock.getBlock(
                // можно заменить на чтение из конфиг файла
                HashGenerator.getRandomBytes(),
                account
        );

        return new BlockChain(genesisBlock);
    }

    void addBlock(Transaction transaction) {
        Block newBlock = new Block(
                transaction.getPrevBlockHash(),
                transaction.getTransactionHash(),
                transaction.getAccount()
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
