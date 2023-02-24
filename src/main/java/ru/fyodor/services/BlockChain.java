package ru.fyodor.services;

import lombok.RequiredArgsConstructor;
import ru.fyodor.models.Block;
import ru.fyodor.models.Transaction;

public class BlockChain {

    private final Block genesisBlock;
    private Block lastBlock;
    //private transient int size;

    public BlockChain(Block genesisBlock) {
        this.genesisBlock = genesisBlock;
        this.lastBlock = genesisBlock;
    }

    public void addBlock(Transaction transaction) {
        Block newBlock = new Block(
                transaction.getPrevBlockHash(),
                transaction.getTransactionHash(),
                transaction.getSignature()
                );

        newBlock.setCurrentHash(calculateNewBlockHash());

        this.lastBlock = newBlock;
//        ++this.size;
    }

    public byte[] getLastBlockHash() {
        return lastBlock.getCurrentHash();
    }

    private byte[] calculateNewBlockHash() {
        return null;
    }
}
