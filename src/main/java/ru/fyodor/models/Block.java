package ru.fyodor.models;

import lombok.*;
import ru.fyodor.client.Account;
import ru.fyodor.services.HashGenerator;

import java.time.Instant;
import java.util.Arrays;
import org.assertj.core.util.Hexadecimals;

@EqualsAndHashCode
@Getter
public class Block {
    private byte[] currentHash; //concated data, instant and prev block hash
    private final byte[] previousHash;
    private final byte[] data;
    private final byte[] signature; //applies on curr hash value
    private final Instant instant;

    public Block(byte[] previousHash, byte[] data, Account account) {
        this.previousHash = previousHash;
        this.data = data;
        this.instant = Instant.now();
        this.currentHash = HashGenerator.calculateHashFromArgs(
                previousHash,
                data,
                instant.toString().getBytes()
        );
        this.signature = account.sign(currentHash);
    }

    @Override
    public String toString() {
        return "Block{\n" +
                "currentHash=" + Hexadecimals.toHexString(currentHash) +
                ", \npreviousHash=" + Hexadecimals.toHexString(previousHash) +
                ", \ndata=" + Hexadecimals.toHexString(data) +
                ", \nsignature=" + Hexadecimals.toHexString(signature) +
                ", \ninstant=" + instant +
                "}\n";
    }
}
