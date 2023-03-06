package ru.fyodor;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.util.Hexadecimals;
import org.junit.jupiter.api.Test;
import ru.fyodor.models.GenesisBlock;
import ru.fyodor.services.HashGenerator;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void genesisTest() throws NoSuchAlgorithmException {

      byte[] bytes = MessageDigest
              .getInstance("SHA-256")
              .digest(GenesisBlock
                      .getBlock(new byte[]{0}, new byte[]{0})
                      .getInstant()
                      .toString()
                      .getBytes(StandardCharsets.UTF_8)
              );

    }

    @Test
    public void hashTest() throws NoSuchAlgorithmException {
        HashGenerator hashGenerator = new HashGenerator();

        byte[] result1 = hashGenerator.generateHash(new byte[]{0,1});
        System.out.println(Hexadecimals.toHexString(result1));

        byte[] result2 = hashGenerator.generateHash(new byte[]{0,1,0});
        System.out.println(Hexadecimals.toHexString(result2));
    }

}
