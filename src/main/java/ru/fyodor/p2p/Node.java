package ru.fyodor.p2p;

import java.util.List;

public class Node {
    /**
     * Управляющий центр сети.
     * Содержит список всех узлов в сети,
     * методы для добавления и удаления узлов из списка,
     * синхронизации блокчейна между узлами.
     * */

    private List<Peer> peers;

    public boolean addPeer(Peer peer) {
        return peers.add(peer);
    }

    public boolean removePeer(Peer peer) {
        return peers.remove(peer);
    }

    public List<Peer> getAllPeers() {
        return peers;
    }
}
