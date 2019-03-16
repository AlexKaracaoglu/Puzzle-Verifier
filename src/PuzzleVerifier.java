/*
 * Puzzle Verifier: PuzzleVerifier.java
 * Homework 3 - Problem 3
 * Group 9
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

/* SOURCES USED:
 * https://gist.github.com/chatton/14110d2550126b12c0254501dde73616
 * https://www.geeksforgeeks.org/socket-programming-in-java/
 * http://cs.lmu.edu/~ray/notes/javanetexamples/
 */

/*
 * PuzzleVerfier is the server. It starts up, waits for PuzzleSolver to solve the puzzle, then receives the
 * BlockchainHeader that the solver sends over, and then verifies whether or not the header is valid
 */

public class PuzzleVerifier {

    private static final Integer TCP_PORT = 5000;

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchAlgorithmException {

        ServerSocket serverSocket = new ServerSocket(TCP_PORT);

        System.out.println("Waiting for the block to be sent from PuzzleSolver...");
        Socket socket = serverSocket.accept();

        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        BlockchainHeader headerOfSentBlock = (BlockchainHeader) objectInputStream.readObject();

        System.out.println("Block was received from Puzzle Solver. Verifying...");

        String hash = Hash.hash(headerOfSentBlock);

        verify(hash, headerOfSentBlock.getDifficulty());

        serverSocket.close();
        socket.close();
    }

    private static void verify(String hash, String difficulty) {
        if (hash.compareTo(difficulty) < 0) {
            System.out.println("Yes. Hashed value was below target.");
        }
        else {
            System.out.println("No. Hashed value was not below target.");
        }
    }

}