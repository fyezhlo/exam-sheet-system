package ru.fyodor.p2p.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.socket.SocketChannel;
import lombok.Getter;
import ru.fyodor.p2p.message.Message;
import ru.fyodor.p2p.message.MsgSerializer;

@Getter
public class ClientChannel {
    private SocketChannel channel;

    public ClientChannel(SocketChannel channel) {
        this.channel = channel;
    }

    public void send(Message msg) {
        ByteBuf buf = Unpooled.copiedBuffer(
                MsgSerializer.serialize(msg)
        );
        channel.writeAndFlush(buf);
    }
}
