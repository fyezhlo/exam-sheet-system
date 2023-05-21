package ru.fyodor.services;

import ru.fyodor.client.AccountService;
import ru.fyodor.generators.HashGenerator;
import ru.fyodor.models.Token;
import ru.fyodor.models.Transaction;


public class TransactionService {
    private final BlockChain blockChain;

    public TransactionService(BlockChain blockChain) {
        this.blockChain = blockChain;
    }

    public void generateTransaction(Token token, AccountService accountService) throws Exception {
        Transaction transaction = new Transaction(
                token,
                accountService,
                this.blockChain.getLastBlockHash(),
                HashGenerator.getRandomBytes()
        );
        blockChain.addBlock(transaction);
    }
}
