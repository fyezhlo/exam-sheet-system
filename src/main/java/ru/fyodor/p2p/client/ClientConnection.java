package ru.fyodor.p2p.client;

import io.netty.channel.socket.SocketChannel;
import lombok.Getter;

@Getter
public class ClientConnection {
    private SocketChannel channel;

    public ClientConnection(SocketChannel channel) {
        this.channel = channel;
    }
}
