package ru.fyodor.p2p;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.List;

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
    private ServerHandler server;
    private ClientHandler client;

    public Node(Peer currPeer) {
        this.currPeer = currPeer;

        this.joinGroup = new NioEventLoopGroup(1);
        this.workGroup = new NioEventLoopGroup();
        this.bootstrap = new ServerBootstrap();
    }

    public void listenConnections(int inetPort) {
        server = new ServerHandler();

        try {
            bootstrap.group(joinGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

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
    }

    public Message receiveMessage(Message message) {
        // operates with bc
    }

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
