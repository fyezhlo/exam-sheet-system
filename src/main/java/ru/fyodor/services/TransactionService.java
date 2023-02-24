package ru.fyodor.services;

import lombok.RequiredArgsConstructor;
import ru.fyodor.models.Token;
import ru.fyodor.models.Transaction;

import java.security.NoSuchAlgorithmException;


public class TransactionService {
    private final BlockChain blockChain;
    private final HashGenerator hashGenerator;

    public TransactionService(BlockChain blockChain) throws NoSuchAlgorithmException {
        this.blockChain = blockChain;
        this.hashGenerator = new HashGenerator();
    }

    public Transaction getTransaction(Token token,
                                      byte[] signature) {

        byte[] randomBytes = getRandomBytes();
        Transaction transaction = new Transaction(
                token,
                signature,
                blockChain.getLastBlockHash(),
                randomBytes

        );

        byte[] transactionHash = hashGenerator.generateHash(getInput(transaction));
        transaction.setTransactionHash(transactionHash);

        return transaction;
    }

    private byte[] getRandomBytes() {
        return null;
    }

    private String getInput(Transaction transaction) {
        return "";
    }
}
