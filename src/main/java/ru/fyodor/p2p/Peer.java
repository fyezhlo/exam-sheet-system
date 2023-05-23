package ru.fyodor.p2p;

import lombok.Getter;
import ru.fyodor.client.Account;

@Getter
public class Peer {
    /**
     * Представляет узел сети
     * Содержит адрес и порт, на которых будет работать узел
     * Аккаунт пользователя блокчейна
     * */

    private Account account; // каждый peer привязан к единственному акку
    private String address;
    private int port;

    public Peer(Account account, String address, int port) {
        this.account = account;
        this.address = address;
        this.port = port;
    }

}
