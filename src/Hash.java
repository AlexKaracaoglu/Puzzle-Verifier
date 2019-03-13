/*
 * Puzzle Verifier: Hash.java
 * Homework 3 - Problem 3
 * Group 9
 */

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * The Hash class provides the helper function hash that takes in a BlockchainHeader and returns its hash value
 */

public class Hash {

    private static final String SHA_256 = "SHA-256";
    private static final String UTF8 = "utf8";
    private static final String TWO_HEX_DIGITS = "%02x";
    private static final String EIGHT_HEX_DIGITS = "%08x";

    public static String hash(BlockchainHeader header) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String headerString = getHeaderString(header);
        MessageDigest messageDigest = MessageDigest.getInstance(SHA_256);
        messageDigest.update(headerString.getBytes(UTF8));
        byte[] digestBytes = messageDigest.digest();
        return buildHexStringFromDigestBytes(digestBytes);
    }

    private static String buildHexStringFromDigestBytes(byte[] digestBytes) {
        StringBuilder hexFormat = new StringBuilder();
        for (byte b: digestBytes) {
            hexFormat.append(String.format(TWO_HEX_DIGITS, b));
        }
        return hexFormat.toString();
    }

    private static String getHeaderString(BlockchainHeader header) {
        return header.getHashOfPreviousBlockHeader() +
                header.getMerkleRoot() +
                String.format(EIGHT_HEX_DIGITS, header.getUnixTime()) +
                header.getDifficulty() +
                String.format(EIGHT_HEX_DIGITS, header.getNonce());
    }

}
