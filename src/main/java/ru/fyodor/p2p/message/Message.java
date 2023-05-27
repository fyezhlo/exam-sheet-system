package ru.fyodor.p2p.message;

import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
public class Message implements Serializable {
    /**
     * Сообщение, передаваемое по сети.
     * Содержит тип сообщения и данные, которые передаются
     * */

    public final MSG_TYPE type;
    public final Object data;

}
