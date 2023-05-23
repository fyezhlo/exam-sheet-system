package ru.fyodor.services;

import ru.fyodor.generators.HashGenerator;
import ru.fyodor.models.Block;
import ru.fyodor.models.Transaction;
import ru.fyodor.p2p.Peer;

import java.util.ArrayList;
import java.util.List;

public class BlockChain {

    private final Block genesisBlock;
    private Block lastBlock;

    private List<Block> chain = new ArrayList<>();
    private List<Peer> peers = new ArrayList<>();

    private BlockChain(Block genesisBlock) {
        this.genesisBlock = genesisBlock;
        this.lastBlock = genesisBlock;
        chain.add(genesisBlock);
        peers.add(genesisBlock.getPeer());
    }

    public static BlockChain generateBlockChain(Peer peer) throws Exception {
        Block genesisBlock = GenesisBlock.getBlock(
                // можно заменить на чтение из конфиг файла
                HashGenerator.getRandomBytes(),
                peer
        );

        return new BlockChain(genesisBlock);
    }

    void addBlock(Transaction transaction) throws Exception {
        Block newBlock = new Block(
                transaction.getPrevBlockHash(),
                transaction.getTransactionHash(),
                transaction.getPeer()
                );

        this.lastBlock = newBlock;
        chain.add(newBlock);
    }

    public boolean checkPeer(Peer peer) {
        /**
         * checks whether passing peer is valid
         * */

        return false;
    }

    byte[] getLastBlockHash() {
        return lastBlock.getCurrentHash();
    }

    public List<Block> getChain() {
        return this.chain;
    }
}
