package ru.fyodor.p2p;

import ru.fyodor.p2p.client.Client;
import ru.fyodor.p2p.message.MSG_TYPE;
import ru.fyodor.p2p.message.Message;
import ru.fyodor.p2p.server.Server;
import ru.fyodor.p2p.server.ServerHandler;
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

    private final Client client;

    public Node(Peer currPeer, TransactionService ts) {
        this.currPeer = currPeer;
        this.ts = ts;

        this.server = new Server(this);
        this.client = new Client(this);
    }

    /**
     * запускает обработчик подключений к узлу
     * прослушивает укаанный порт
     * обрабатывает входящие сообщения
     * */
    public void listenConnections(int inetPort) {
        this.server.runServer(
                new ServerHandler(this),
                inetPort
        );
    }

    public void connectToNodes() {
        for (Peer peer : peers) {
            this.client.connectToServer(
                    peer.getHost(),
                    peer.getPort()
            );
        }
    }

    public void sendMessage(Message message) {
        this.client.sendMessage(message);
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
        handleMap.put(MSG_TYPE.JOIN_CHAIN, joinChain);
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
