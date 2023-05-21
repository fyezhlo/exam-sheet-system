package ru.fyodor;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.util.Hexadecimals;
import org.junit.jupiter.api.Test;
import ru.fyodor.client.Account;
import ru.fyodor.client.AccountService;
import ru.fyodor.models.Block;
import ru.fyodor.models.Collection;
import ru.fyodor.models.Token;
import ru.fyodor.client.AccountServiceImpl;
import ru.fyodor.services.BlockChain;
import ru.fyodor.generators.HashGenerator;
import ru.fyodor.generators.MerkleTree.MerkleTree;
import ru.fyodor.services.TransactionService;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.fyodor.generators.HashGenerator.getRandomBytes;


public class AppTest 
{
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void logTest() throws Exception {
        Logger logger = LogManager.getLogger(App.class.getName());
        logger.error("testing ERROR message log");
    }

    @Test
    public void hashTest() throws NoSuchAlgorithmException {
        HashGenerator hashGenerator = new HashGenerator();

        byte[] result1 = hashGenerator.generateHash(new byte[]{0,1});
        System.out.println(Hexadecimals.toHexString(result1));

        byte[] result2 = hashGenerator.generateHash(new byte[]{0,1,0});
        System.out.println(Hexadecimals.toHexString(result2));
    }

    @Test
    public void getRandomBytesTest() throws NoSuchAlgorithmException {
        byte[] randomBytes = new byte[32];
        ThreadLocalRandom
                .current()
                .nextBytes(randomBytes);

        System.out.println(Hexadecimals.toHexString(randomBytes));

        HashGenerator hashGenerator = new HashGenerator();
        System.out.println(Hexadecimals.toHexString(hashGenerator.generateHash(randomBytes)));
    }

    @Test
    public void merkleTreeTest() {
       List<byte[]> list = asList(
          new byte[] {0},
          new byte[] {1},
          new byte[] {0, 1}
       );

        System.out.println(Hexadecimals.toHexString(MerkleTree.generateTree(list).getHash()));
    }

    @Test
    public void transactionTest() throws Exception {
        AccountService as = new AccountServiceImpl();
        Account account = as.createAccount("seed".getBytes());

        BlockChain blockChain = BlockChain.generateBlockChain(as);
        TransactionService ts = new TransactionService(blockChain);

        Token token = new Token(
                getRandomBytes(),
                new Collection(
                        as,
                        getRandomBytes()
                )
        );


                                    // заменить на ссылку на аккаунт, к-й подп. тр-ю
                                    // в тс будет осуществляться операция подписания
                                    // |
                                    // V
        ts.generateTransaction(token, as);
        ts.generateTransaction(token, as);

        System.out.print("Genesis block: ");
        for (Block block : blockChain.getChain()) {
            System.out.println(block);
        }
    }
}
