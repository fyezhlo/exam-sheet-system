package ru.fyodor.p2p;

import java.io.Serializable;

public class Message implements Serializable {
    /**
     * Сообщение, передаваемое по сети.
     * Содержит тип сообщения и данные, которые передаются
     * */

    MSG_TYPE type;
    Object data;

}
