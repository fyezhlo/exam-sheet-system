package ru.fyodor;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

/*        byte[] bytes = GenesisBlock
                .getBlock(new byte[]{0})
                .getInstant()
                .toString()
                .getBytes(StandardCharsets.UTF_8);*/


      for (byte b : bytes) {
          System.out.print(Integer.toHexString(b));
      }
    }

    @Test
    public void hashTest() throws NoSuchAlgorithmException {
        HashGenerator hashGenerator = new HashGenerator();

    }
}
