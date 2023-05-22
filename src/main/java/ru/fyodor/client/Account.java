package ru.fyodor.client;

import lombok.Getter;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

@Getter
public class Account {
    private KeyPair keyPair;
    private byte[] publicKey;
    //private byte[] address;


    public Account(KeyPair keyPair) {
        this.keyPair = keyPair;
        this.publicKey = keyPair.getPublic().getEncoded();
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
