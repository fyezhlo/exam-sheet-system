package ru.fyodor.services;

import ru.fyodor.generators.HashGenerator;
import ru.fyodor.models.Token;
import ru.fyodor.models.Transaction;
import ru.fyodor.p2p.Peer;


public class TransactionService {
    private final BlockChain blockChain;

    public TransactionService(BlockChain blockChain) {
        this.blockChain = blockChain;
    }

    public void generateTransaction(Token token, Peer peer) {
        Transaction transaction = new Transaction(
                token,
                peer,
                this.blockChain.getLastBlockHash(),
                HashGenerator.getRandomBytes()
        );
        try {
            blockChain.addBlock(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] getLastBlock() {
        return blockChain.getLastBlockHash();
    }
}
