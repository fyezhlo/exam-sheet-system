package ru.fyodor;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{

    public static ArrayList<BlockModel> blockchain = new ArrayList<BlockModel>();

    public static Boolean isChainValid() {
        BlockModel currentBlock;
        BlockModel previousBlock;

        //loop through blockchain to check hashes:
        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //compare registered hash and calculated hash:
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }
    public static void main( String[] args ) {
            //add our blocks to the blockchain ArrayList:
            blockchain.add(new BlockModel("Hi im the first block", "0"));
            blockchain.add(new BlockModel("Yo im the second block",blockchain.get(blockchain.size()-1).hash));
            blockchain.add(new BlockModel("Hey im the third block",blockchain.get(blockchain.size()-1).hash));

            for (BlockModel block : blockchain) {
                System.out.println(block);
            }
    }
}
