/*
 * Puzzle Verifier: BlockchainHeader.java
 * Homework 3 - Problem 3
 * Group 9
 */

import java.io.Serializable;
import java.util.Random;

public class BlockchainHeader implements Serializable {

    private String hashOfPreviousBlockHeader;

    private String merkleRoot;

    private Long unixTime;

    private String difficulty;

    private Long nonce;

    public BlockchainHeader(String difficulty) {
        Random random = new Random();

        Long random1 = random.nextLong();
        Long random2 = random.nextLong();

        this.hashOfPreviousBlockHeader = Long.toHexString(random1);
        this.merkleRoot = Long.toHexString(random2);
        this.unixTime = System.currentTimeMillis() / 1000L;
        this.difficulty = difficulty;
    }

    public String getHashOfPreviousBlockHeader() {
        return hashOfPreviousBlockHeader;
    }

    public void setHashOfPreviousBlockHeader(String hashOfPreviousBlockHeader) {
        this.hashOfPreviousBlockHeader = hashOfPreviousBlockHeader;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public void setMerkleRoot(String merkleRoot) {
        this.merkleRoot = merkleRoot;
    }

    public Long getUnixTime() {
        return unixTime;
    }

    public void setUnixTime(Long unixTime) {
        this.unixTime = unixTime;
    }

    public void setUnixTime() {
        this.unixTime = System.currentTimeMillis() / 1000L;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Long getNonce() {
        return nonce;
    }

    public void setNonce(Long nonce) {
        this.nonce = nonce;
    }
}
