package ru.fyodor.p2p;

import io.netty.bootstrap.Bootstrap;
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

    private Account account;
    private String address;
    private int port;

    public Peer(Account account, String address, int port) {
        this.account = account;
        this.address = address;
        this.port = port;
    }

    public boolean sendMessage(Message message) {
        EventLoopGroup group = new NioEventLoopGroup(); //?
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(address, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ObjectEncoder(), new ObjectDecoder(ClassResolvers.cacheDisabled(null)), new Client());
                        }
                    });
            ChannelFuture future = bootstrap.connect().sync();
            future.channel().writeAndFlush(message).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

        return false;
    }
}
