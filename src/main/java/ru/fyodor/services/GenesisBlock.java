package ru.fyodor.services;

import ru.fyodor.models.Block;
import ru.fyodor.p2p.Peer;

class GenesisBlock{

    private static Block genesisBlock = null;

    private GenesisBlock(byte[] data, Peer peer) throws Exception {
        genesisBlock = new Block(
                new byte[]{0},
                data,
                peer
        );
    }
    public static Block getBlock(byte[] data, Peer peer) throws Exception {
        if (genesisBlock == null) {
            new GenesisBlock(data, peer);
        }
        return genesisBlock;
    }

}
