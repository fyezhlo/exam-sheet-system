package ru.fyodor.p2p;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
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
    private final ServerBootstrap bootstrap;
    private EventLoopGroup joinGroup;
    private EventLoopGroup workGroup;
    private Peer currPeer;
    private List<Peer> peers;
    private TransactionService ts;

    public Node(Peer currPeer, TransactionService ts) {
        this.currPeer = currPeer;
        this.ts = ts;

        this.joinGroup = new NioEventLoopGroup(1);
        this.workGroup = new NioEventLoopGroup();
        this.bootstrap = new ServerBootstrap();
    }

    /**
     * запускает обработчик подключений к узлу
     * прослушивает укаанный порт
     * обрабатывает входящие сообщения
     * */
    public void listenConnections(int inetPort) {
        ServerHandler serverHandler = new ServerHandler(this);
        try {
            bootstrap.group(joinGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(serverHandler);
                        }
                    });
            ChannelFuture future = bootstrap.bind(inetPort).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
            joinGroup.shutdownGracefully();
        }

    }

    public boolean sendMessage(Message message) {
        // operates with bc

        return false;
    }

    public Message receiveMessage(Message message) {
        handleMap.get(message.type);
        return null;
    }

    private BiConsumer<Message, TransactionService> addBlock = (Message msg, TransactionService ts) -> {

    };
    private BiConsumer<Message, TransactionService> getLastBlock = (Message msg, TransactionService ts) -> {

    };
    private BiConsumer<Message, TransactionService> joinChain = (Message msg, TransactionService ts) -> {

    };
    private Map<MESSAGE_TYPE, BiConsumer<Message, TransactionService>> handleMap = new HashMap<>();
    {
        handleMap.put(MESSAGE_TYPE.ADD_NEW_BLOCK, addBlock);
        handleMap.put(MESSAGE_TYPE.GET_LAST_BLOCK, getLastBlock);
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
