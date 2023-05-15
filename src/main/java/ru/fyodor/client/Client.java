package ru.fyodor.client;

import java.util.Optional;

public interface Client {
    /**
     * Метод для добавления нового пользавателя в систему
     * На вход подается сид фраза в виде байт-массива
     * Возвращает ссылку на созданный инстанс для конкретного пользователя
     * */
    public Client createAccount(byte[] seedPhrase);

    /**
     * Метод для загрузки нового документа в систему
     * На вход подается жсон-объект в виде байт-массива
     * Возвращает id документа в системе в случае успешного добавления
     * В противном случае возвращает null
     * */

    public Optional<byte[]> pushData(byte[] data);

    /**
     * Метод для получения валидного жсон-объекта
     * На вход подается id документа в системе
     * Возвращает документ в виде байт-массива в случае когда id существует
     * В противном случае возвращает null
     */
    public Optional<byte[]> getData(byte[] id);

}
