package ru.fyodor.client;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.fyodor.generators.HashGenerator;
import ru.fyodor.models.Collection;
import ru.fyodor.models.Token;
import ru.fyodor.p2p.Peer;
import ru.fyodor.services.BlockChain;
import ru.fyodor.services.TransactionService;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Service
@Getter
public class AccountServiceImpl extends AccountService {


    private Peer peer;
    private Collection collection;
    private TransactionService transactionService;

    @Override
    public Account createAccount(byte[] seed, String address, int port) {
        KeyPair kp = null;
        try {
            kp = generateKeyPair(seed);
        } catch (NoSuchAlgorithmException e) {
            //this will never happen
            System.out.println(e.getMessage());
        }
        Arrays.fill(seed, (byte) 0);

        Account account = new Account(kp);
        collection = new Collection(account, HashGenerator.getRandomBytes());
        peer = new Peer(
                account,
                address,
                port
        );

        try {
            joinChain(BlockChain.generateBlockChain(peer));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public byte[] pushData(byte[] data) {
        Token token = new Token(data, collection);
        collection.getTokenList().add(token);
        try {
            transactionService.generateTransaction(token, peer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token.getTokenId();
    }

    @Override
    public byte[] getData(byte[] id) {
        for (Token token : collection.getTokenList()) {
            if (Arrays.equals(token.getTokenId(), id)) {
                return token.getDataHash();
            }
        }
        return null;
    }

    public void joinChain(BlockChain blockChain) {
        transactionService = new TransactionService(blockChain);
    }
}
