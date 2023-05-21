package ru.fyodor.models;

import lombok.Getter;
import ru.fyodor.client.AccountService;
import ru.fyodor.generators.HashGenerator;

@Getter
public class Transaction {
    private final Token token;
    private final AccountService accountService;
    private byte[] transactionHash;
    private final byte[] prevBlockHash;
    private final byte[] randomBytes;

    public Transaction(Token token, AccountService accountService, byte[] prevBlockHash, byte[] randomBytes) {
        this.token = token;
        this.accountService = accountService;
        this.prevBlockHash = prevBlockHash;
        this.randomBytes = randomBytes;

        this.transactionHash = HashGenerator.calculateHashFromArgs(
                token.getDataHash(),
                prevBlockHash,
                randomBytes
        );
    }
}
