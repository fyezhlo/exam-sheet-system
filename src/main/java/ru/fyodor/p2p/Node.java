package ru.fyodor.p2p;

import ru.fyodor.services.TransactionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class Node {
    /**
     * Управляющий центр сети.
     * Содержит список всех узлов в сети,
     * методы для добавления и удаления узлов из списка,
     * подключения к другим узлам, отправки и получения сообщений
     * синхронизации блокчейна между узлами.
     * */

    private Peer currPeer;
    private List<Peer> peers;
    private TransactionService ts;
    private final Server server;

    public Node(Peer currPeer, TransactionService ts) {
        this.currPeer = currPeer;
        this.ts = ts;

        this.server = new Server(this);
    }

    /**
     * запускает обработчик подключений к узлу
     * прослушивает укаанный порт
     * обрабатывает входящие сообщения
     * */
    public void listenConnections(int inetPort) {
        this.server.run(
                new ServerHandler(this),
                inetPort
        );
    }

    public boolean sendMessage(Message message) {
        // operates with bc

        return false;
    }

    public void receiveMessage(Message msg) {
        handle(
                handleMap.get(msg.type),
                msg
        );
    }

    private void handle(BiConsumer<Message, TransactionService> consumer, Message msg) {
        consumer.accept(msg, this.ts);
    }

    private BiConsumer<Message, TransactionService> addBlock = (Message msg, TransactionService ts) -> {

    };
    private BiConsumer<Message, TransactionService> getLastBlock = (Message msg, TransactionService ts) -> {

    };
    private BiConsumer<Message, TransactionService> joinChain = (Message msg, TransactionService ts) -> {

    };
    private Map<MSG_TYPE, BiConsumer<Message, TransactionService>> handleMap = new HashMap<>();
    {
        handleMap.put(MSG_TYPE.ADD_NEW_BLOCK, addBlock);
        handleMap.put(MSG_TYPE.GET_LAST_BLOCK, getLastBlock);
    }

    private void addPeer(Peer peer) {
        peers.add(peer);
    }

    private boolean removePeer(Peer peer) {
        return peers.remove(peer);
    }

    private List<Peer> getAllPeers() {
        return peers;
    }
}
