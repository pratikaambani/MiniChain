package com.practise.block;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pratik Ambani on 04/06/18.
 */
public class MiniChain {

    // how many block to forge after the genesis
    private static final int TO_FORGE = 20;

    public static void main(String[] args) {
        // creating the blockchain
        List<Block> blockchain = new ArrayList();

        // creating genesis
        blockchain.add(new Block());
        Block previousBlock = blockchain.get(0);

        // populating the chain
        Block newBlock;
        for (int i = 0; i < TO_FORGE; ++i) {
            // creating the new block
            newBlock = Block.getNextBlock(previousBlock);

            // adding the new block
            blockchain.add(newBlock);
            previousBlock = newBlock;

            // displaying its info
            System.out.println(String.join(
                    "\n",
                    "Block #" + newBlock.getIndex() + " has been added to the blockchain !",
                    "Hash: " + newBlock.getCurrentHash(),
                    "Prev: " + newBlock.getPreviousHash(),
                    ""
            ));
        }
    }
}
