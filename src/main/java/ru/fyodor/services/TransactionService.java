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

    public void generateTransaction(Token token, Peer peer) throws Exception {
        Transaction transaction = new Transaction(
                token,
                peer,
                this.blockChain.getLastBlockHash(),
                HashGenerator.getRandomBytes()
        );
        blockChain.addBlock(transaction);
    }
}
