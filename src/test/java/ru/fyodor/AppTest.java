package ru.fyodor;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.fyodor.models.GenesisBlock;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */


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
}
