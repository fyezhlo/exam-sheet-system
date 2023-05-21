package ru.fyodor.models;

import lombok.Getter;
import ru.fyodor.client.Account;
import ru.fyodor.generators.HashGenerator;

@Getter
public class Transaction {
    private final Token token;
    private final Account account;
    private byte[] transactionHash;
    private final byte[] prevBlockHash;
    private final byte[] randomBytes;

    public Transaction(Token token, Account account, byte[] prevBlockHash, byte[] randomBytes) {
        this.token = token;
        this.account = account;
        this.prevBlockHash = prevBlockHash;
        this.randomBytes = randomBytes;

        this.transactionHash = HashGenerator.calculateHashFromArgs(
                token.getDataHash(),
                prevBlockHash,
                randomBytes
        );
    }
}
