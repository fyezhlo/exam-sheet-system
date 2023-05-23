package ru.fyodor.p2p;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.Getter;
import ru.fyodor.client.Account;

import java.net.InetSocketAddress;

@Getter
public class Peer {
    /**
     * Представляет узел сети
     * Содержит адрес и порт, на которых будет работать узел
     * Методы для отправки и получения сообщений
     * */

    private Account account; // каждый peer привязан к единственному акку
    private String address;
    private int port;
    private Server server;
    private Client client;

    public Peer(Account account, String address, int port) {
        this.account = account;
        this.address = address;
        this.port = port;
    }

    public boolean sendMessage(Message message) {

    }

    public Message receiveMessage(ByteBuf bytes) {

    }
}
