package ru.fyodor.p2p.message;

import java.io.Serializable;

public class Message implements Serializable {
    /**
     * Сообщение, передаваемое по сети.
     * Содержит тип сообщения и данные, которые передаются
     * */

    public MSG_TYPE type;
    public Object data;

}
