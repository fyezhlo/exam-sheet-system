package ru.fyodor.services;

import ru.fyodor.client.Account;
import ru.fyodor.generators.HashGenerator;
import ru.fyodor.models.Token;
import ru.fyodor.models.Transaction;


public class TransactionService {
    private final BlockChain blockChain;

    public TransactionService(BlockChain blockChain) {
        this.blockChain = blockChain;
    }

    public void generateTransaction(Token token, Account account) {
        Transaction transaction = new Transaction(
                token,
                account,
                this.blockChain.getLastBlockHash(),
                HashGenerator.getRandomBytes()
        );
        blockChain.addBlock(transaction);
    }
}
