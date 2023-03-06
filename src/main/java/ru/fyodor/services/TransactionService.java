package ru.fyodor.services;

import lombok.RequiredArgsConstructor;
import ru.fyodor.models.Token;
import ru.fyodor.models.Transaction;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ThreadLocalRandom;


public class TransactionService {
    private final BlockChain blockChain;
    private final HashGenerator hashGenerator;

    public TransactionService(BlockChain blockChain) throws NoSuchAlgorithmException {
        this.blockChain = blockChain;
        this.hashGenerator = new HashGenerator();
    }

    public Transaction getTransaction(Token token,
                                      byte[] signature) {

        Transaction transaction = new Transaction(
                token,
                signature,
                blockChain.getLastBlockHash(),
                getRandomBytes()
        );

        byte[] transactionHash = hashGenerator.generateHash(getInput(transaction));
        transaction.setTransactionHash(transactionHash);

        return transaction;
    }

    private byte[] getRandomBytes() {
        byte[] randomBytes = new byte[64];

        ThreadLocalRandom
                .current()
                .nextBytes(randomBytes);

        return randomBytes;
    }

    private byte[] getInput(Transaction transaction) {
        return null;
    }
}
