package ru.fyodor.models;

import lombok.Getter;
import ru.fyodor.generators.HashGenerator;
import ru.fyodor.p2p.Peer;

@Getter
public class Transaction {
    private final Token token;
    private final Peer peer;
    private byte[] transactionHash;
    private final byte[] prevBlockHash;
    private final byte[] randomBytes;

    public Transaction(Token token, Peer peer, byte[] prevBlockHash, byte[] randomBytes) {
        this.token = token;
        this.peer = peer;
        this.prevBlockHash = prevBlockHash;
        this.randomBytes = randomBytes;

        this.transactionHash = HashGenerator.calculateHashFromArgs(
                token.getDataHash(),
                prevBlockHash,
                randomBytes
        );
    }
}
