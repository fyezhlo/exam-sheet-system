package ru.fyodor.p2p.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.fyodor.p2p.message.Message;

import java.io.IOException;

public class MsgSerializer {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static byte[] serialize(Message msg) {
        byte[] bytes = new byte[]{0};
        try {
            bytes = mapper.writeValueAsBytes(msg);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return bytes;
    }

    public static Message deserialize(byte[] bytes) {
        Message msg = null;
        try {
            msg = mapper.readValue(bytes, Message.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
