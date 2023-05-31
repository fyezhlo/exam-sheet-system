package ru.fyodor.p2p;

import ru.fyodor.generators.HashGenerator;
import ru.fyodor.models.Collection;
import ru.fyodor.models.Token;
import ru.fyodor.p2p.client.Client;
import ru.fyodor.p2p.message.Message;
import ru.fyodor.p2p.message.MsgSerializer;
import ru.fyodor.p2p.server.Server;
import ru.fyodor.p2p.server.ServerHandler;
import ru.fyodor.services.TransactionService;

import java.util.ArrayList;
import java.util.List;
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

        this.peers = new ArrayList<>();
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
        for (Peer peer : this.peers) {
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
        switch (msg.type) {
            case ADD_NEW_BLOCK: addBlock(msg, this.ts);
            case GET_LAST_BLOCK: getLastBlock();
            case JOIN_CHAIN: addPeer((Peer) msg.data);
        }
    }

    private void handle(BiConsumer<Message, TransactionService> consumer, Message msg) {
        consumer.accept(msg, this.ts);
    }

    private void addBlock(Message msg, TransactionService ts) {
        Token token = new Token(
                MsgSerializer.serialize(msg),
                new Collection(currPeer, HashGenerator.getRandomBytes())
        );

        ts.generateTransaction(
                token,
                currPeer
        );
    };
    private byte[] getLastBlock () {
        return ts.getLastBlock();
    };

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
