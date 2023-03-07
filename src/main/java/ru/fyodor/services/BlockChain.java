package ru.fyodor.services;

import lombok.RequiredArgsConstructor;
import ru.fyodor.models.Block;
import ru.fyodor.models.Collection;
import ru.fyodor.models.Transaction;
import ru.fyodor.services.MerkleTree.MerkleTree;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlockChain {

    private final Block genesisBlock;
    private Block lastBlock;

    private List<Block> chain = new ArrayList<>();

    public BlockChain(Block genesisBlock) {
        this.genesisBlock = genesisBlock;
        this.lastBlock = genesisBlock;
        chain.add(genesisBlock);
    }

    public void addBlock(Transaction transaction) {
        Block newBlock = new Block(
                transaction.getPrevBlockHash(),
                transaction.getTransactionHash(),
                transaction.getSignature()
                );

        this.lastBlock = newBlock;
        chain.add(newBlock);
    }

    public byte[] getLastBlockHash() {
        return lastBlock.getCurrentHash();
    }
}
