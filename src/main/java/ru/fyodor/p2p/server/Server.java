package ru.fyodor.p2p.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import ru.fyodor.p2p.Node;

public class Server {
    private Node node;
    private ServerBootstrap bootstrap;
    private EventLoopGroup joinGroup;
    private EventLoopGroup workGroup;

    public Server(Node node) {
        this.node = node;
    }

    public void runServer(ChannelInboundHandlerAdapter serverHandler,
                          int inetPort) {
        this.joinGroup = new NioEventLoopGroup(1);
        this.workGroup = new NioEventLoopGroup();
        this.bootstrap = new ServerBootstrap();

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
}
