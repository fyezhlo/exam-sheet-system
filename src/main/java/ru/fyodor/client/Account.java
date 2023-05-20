package ru.fyodor.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.Arrays;
import java.util.Optional;

@Getter
public abstract class Account {
    private KeyPair keyPair;
    private byte[] publicKey;
    private byte[] address;

    protected Account() {
    }

    /**
     * Метод для добавления нового пользавателя в систему
     * На вход конструктора подается сид фраза в виде байт-массива
     * Возвращает ссылку на созданный инстанс для конкретного пользователя
     * */
    public abstract Account createAccount(byte[] seedPhrase);

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

    protected boolean generateKeyPair(byte[] seed) throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        SecureRandom random = SecureRandom.getInstanceStrong();
        random.setSeed(seed);
        keyGen.initialize(2048, random);
        KeyPair keyPair = keyGen.generateKeyPair();

        Arrays.fill(seed, (byte) 0);

        this.keyPair = keyPair;
        this.publicKey = keyPair.getPublic().getEncoded();
        return false;
    }
    /**
     * Метод, генерирующий подпись на основе полей объекта Account
     * */
    public byte[] sign(byte[] data) throws NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        byte[] encryptedData = cipher.doFinal(data);

        return encryptedData;
    }
}
