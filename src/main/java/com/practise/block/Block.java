package com.practise.block;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Pratik Ambani on 04/06/18.
 */
class Block {
    private int index;
    private long timestamp;
    private String currentHash;
    private String data;
    private String previousHash;

    Block() {
        index = 0;
        timestamp = System.currentTimeMillis();
        data = "This is block 0";
        previousHash = "0";
        currentHash = Block.hash(this);
    }

    private Block(int _index, long _timestamp, String _data, String _previousHash) {
        index = _index;
        timestamp = _timestamp;
        data = _data;
        previousHash = _previousHash;
        currentHash = Block.hash(this);
    }

    static Block getNextBlock(Block lastBlock) {
        return new Block(
                lastBlock.index + 1,
                System.currentTimeMillis(),
                "Hey! I'm block " + String.valueOf(lastBlock.index + 1),
                lastBlock.getCurrentHash()
        );
    }

    private static String hash(Block b) {
        String hash = null;
        String HASH_ALGORITHM = "SHA-256";

        // Storing block content into a String
        StringBuilder blockContent = new StringBuilder();
        blockContent.append(b.index)
                .append(b.timestamp)
                .append(b.data)
                .append(b.currentHash)
                .append(b.previousHash);

        try {
            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);

            // preparing hashing content
            byte[] content = md.digest(
                    (blockContent.toString()).getBytes()
            );

            // hashing the bock
            hash = String.format(
                    "%064x", new BigInteger(1, content)
            );
        } catch (NoSuchAlgorithmException e) {
            // exiting if impossible to use the HASH_ALGORITHM
            System.err.println("[FAILURE] " + HASH_ALGORITHM + " not supported.");
            System.exit(1);
        }
        return hash;
    }

    String getCurrentHash() {
        return currentHash;
    }

    int getIndex() {
        return index;
    }

    String getPreviousHash() {
        return previousHash;
    }
}
