package ru.fyodor.p2p;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class Client extends ChannelInboundHandlerAdapter {
    /**
     * Клиент для обработки сетевых сообщений
     * */

    /**
     * Обрабатывает полученные сообщения и реагирует на них
     * */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    /**
     * Обрабатывает ошибки полученные при обработке сообщений
     * */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
