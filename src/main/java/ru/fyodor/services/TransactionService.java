package ru.fyodor.services;

import ru.fyodor.models.Token;
import ru.fyodor.models.Transaction;


public class TransactionService {
    private final BlockChain blockChain;
    private final static HashGenerator hashGenerator = new HashGenerator();

    public TransactionService(BlockChain blockChain) {
        this.blockChain = blockChain;
    }

    public void generateTransaction(Token token, byte[] signature) {
        Transaction transaction = new Transaction(
                token,
                signature,
                this.blockChain.getLastBlockHash(),
                HashGenerator.getRandomBytes()
        );
        blockChain.addBlock(transaction);
    }

    private byte[] getInput(Transaction transaction) {
        return null;
    }
}
