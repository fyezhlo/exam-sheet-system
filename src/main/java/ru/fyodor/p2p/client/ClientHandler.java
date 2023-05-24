package ru.fyodor.p2p.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import ru.fyodor.p2p.Node;

public class ClientHandler extends ChannelInitializer<SocketChannel> {
    private Node node;

    public ClientHandler(Node node) {
        this.node = node;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

    }
}
