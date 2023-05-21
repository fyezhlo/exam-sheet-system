package ru.fyodor.client;

import lombok.Getter;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.Arrays;
import java.util.Optional;

@Getter
public abstract class AccountService {

    /**
     * Метод для добавления нового пользавателя в систему
     * На вход конструктора подается сид фраза в виде байт-массива
     * Возвращает ссылку на созданный инстанс для конкретного пользователя
     * */
    public abstract Account createAccount(byte[] seed);

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
     * Генерация пар ключей
     * */
    protected KeyPair generateKeyPair(byte[] seed) throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        SecureRandom random = SecureRandom.getInstanceStrong();
        random.setSeed(seed);
        keyGen.initialize(2048, random);
        KeyPair keyPair = keyGen.generateKeyPair();

        Arrays.fill(seed, (byte) 0);
        return keyPair;
    }
}
