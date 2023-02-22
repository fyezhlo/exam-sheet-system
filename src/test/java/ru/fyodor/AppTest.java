package ru.fyodor;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.fyodor.models.GenesisBlock;

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
    public void genesisTest() {
        System.out.println(GenesisBlock.getBlock(new byte[]{0}));
    }
}
