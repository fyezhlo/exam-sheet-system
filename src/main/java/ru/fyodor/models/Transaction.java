package ru.fyodor.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.fyodor.services.TransactionService;

@Getter
public class Transaction {
    private final Token token;
    private final byte[] signature;
    private byte[] transactionHash;
    private final byte[] prevBlockHash;
    private final byte[] randomBytes;

    public Transaction(Token token, byte[] signature, byte[] prevBlockHash, byte[] randomBytes) {
        this.token = token;
        this.signature = signature;
        this.prevBlockHash = prevBlockHash;
        this.randomBytes = randomBytes;

        this.transactionHash = TransactionService.calculateNewTransHash();
    }
}
