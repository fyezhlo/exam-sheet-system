package ru.fyodor.p2p;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * Обрабатывает полученные сообщения и реагирует на них
     * */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        sendBuf(msg);
    }

    public Message sendBuf(Object msg) {
        ByteBuf buf = (ByteBuf) msg;
        return MsgSerializer.deserialize(buf.array());
    }

    /**
     * Обрабатывает ошибки полученные при обработке сообщений
     * */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
