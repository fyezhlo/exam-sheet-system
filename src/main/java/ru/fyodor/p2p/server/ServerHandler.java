package ru.fyodor.p2p.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import ru.fyodor.p2p.message.Message;
import ru.fyodor.p2p.message.MsgSerializer;
import ru.fyodor.p2p.Node;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    private Node node;

    public ServerHandler(Node node) {
        this.node = node;
    }

    /**
     * Обрабатывает полученные сообщения и реагирует на них
     * */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        receiveMessage(
                MsgSerializer.deserialize(buf.array())
        );
        buf.release();
    }

    private void receiveMessage(Message msg) {
        node.receiveMessage(msg);
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
