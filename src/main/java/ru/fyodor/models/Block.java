package ru.fyodor.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.assertj.core.util.Hexadecimals;
import ru.fyodor.generators.HashGenerator;
import ru.fyodor.p2p.Peer;

import java.time.Instant;

@EqualsAndHashCode
@Getter
public class Block {
    private byte[] currentHash; //concated data, instant and prev block hash
    private final byte[] previousHash;
    private final byte[] data;
    private final byte[] signature; //applies on curr hash value
    private final Instant instant;

    private final Peer peer;

    public Block(byte[] previousHash, byte[] data, Peer peer) throws Exception {
        this.previousHash = previousHash;
        this.data = data;
        this.instant = Instant.now();
        this.peer = peer;
        this.currentHash = HashGenerator.calculateHashFromArgs(
                previousHash,
                data,
                instant.toString().getBytes()
        );
        this.signature = peer.getAccount().sign(currentHash);
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
