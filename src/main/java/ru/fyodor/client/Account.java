package ru.fyodor.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
public abstract class Account {
    private final byte[] seedPhrase;
    private byte[] publicKey;
    private byte[] address;

    public Account(byte[] seedPhrase) {
        this.seedPhrase = seedPhrase;
    }

    /**
     * Метод для добавления нового пользавателя в систему
     * На вход конструктора подается сид фраза в виде байт-массива
     * Возвращает ссылку на созданный инстанс для конкретного пользователя
     * */
    public abstract Account createAccount();

    /**
     * Метод для загрузки нового документа в систему
     * На вход подается жсон-объект в виде байт-массива
     * Возвращает id документа в системе в случае успешного добавления
     * В противном случае возвращает null
     * */
    public abstract Optional<byte[]> pushData(byte[] data);

    /**
     * Метод для получения валидного жсон-объекта
     * На вход подается id документа в системе
     * Возвращает документ в виде байт-массива в случае когда id существует
     * В противном случае возвращает null
     */
    public abstract Optional<byte[]> getData(byte[] id);


    /**
     * Метод, генерирующий подпись на основе полей объекта Account
     * */
    public byte[] sign(byte[] data) {
        return null;
    }
}
