/*
 * Puzzle Verifier: PuzzleSolver.java
 * Homework 3 - Problem 3
 * Group 9
 */

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

/* SOURCES USED:
 * https://gist.github.com/chatton/14110d2550126b12c0254501dde73616
 * https://www.geeksforgeeks.org/socket-programming-in-java/
 * http://cs.lmu.edu/~ray/notes/javanetexamples/
 */

public class PuzzleSolver {

    private static final Integer SIXTY_FOUR = 64;
    private static final String HEX_FORMAT = "[0-9a-f]+";
    private static final String IP_ADDRESS = "127.0.0.1";
    private static final Integer TCP_PORT = 5000;

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        Socket socket = new Socket(IP_ADDRESS, TCP_PORT);
        System.out.println("Connection was made. Solving the puzzle...");

        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        Settings settings = new Settings();
        String difficulty = settings.getDifficulty();
        checkValidDifficultyFormat(difficulty);

        BlockchainHeader header = new BlockchainHeader(difficulty);

        Long nonce = 0L;
        header.setNonce(nonce);

        String hashValue = Hash.hash(header);

        while (hashValue.compareTo(header.getDifficulty()) >= 0) {
            nonce++;
            header.setNonce(nonce);
            hashValue = Hash.hash(header);
        }

        System.out.println("Sending message to the Puzzle Verifier...");
        objectOutputStream.writeObject(header);

        socket.close();
    }

    private static void checkValidDifficultyFormat(String difficulty) {
        if (difficulty.length() != SIXTY_FOUR) {
            throw new IllegalArgumentException("Settings hex string must be of length 64");
        }
        if (!difficulty.matches(HEX_FORMAT)) {
            throw new IllegalArgumentException("Settings string must include only hex digits");
        }
    }
}
