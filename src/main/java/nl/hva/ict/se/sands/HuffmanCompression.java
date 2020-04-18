package nl.hva.ict.se.sands;

import java.io.InputStream;
<<<<<<< HEAD
import java.util.*;

public class HuffmanCompression {
    private final String text;
    // alphabet size of extended ASCII
    private static final int R = 256;
    private Node root;
    private char[] textCharacters;
    private final Map<Character, String> map;
    private int[] freq;
    Map<Character, Integer> characterFreq = new HashMap<>();

    public HuffmanCompression(String text) {
        this.text = text;
        textCharacters = this.text.toCharArray();

        map = new HashMap<>();

        compress();
=======
import java.util.Map;
import java.util.Scanner;

public class HuffmanCompression {
    private final String text;

    public HuffmanCompression(String text) {
        this.text = text;
>>>>>>> parent of d0ed098... Progress
    }

    public HuffmanCompression(InputStream input) {
        Scanner sc = new Scanner(input);
        sc.useDelimiter("\\Z");

        text = sc.next();
<<<<<<< HEAD
        textCharacters = this.text.toCharArray();
        map = new HashMap<>();

        compress();
=======
>>>>>>> parent of d0ed098... Progress
    }

    /**
     * Returns the compression ratio assuming that every characters in the text uses 8 bits.
     * @return the compression ratio.
     */
    public double getCompressionRatio() {
        return 0.0;
    }

    /**
     * Compresses the text that was provided to the constructor.
     * @return
     */
    public String compress() {
<<<<<<< HEAD
        // build Huffman trie
        buildTrie();
        buildCode();


        return result;
    }

    // build the Huffman trie given frequencies
    private Node buildTrie() {

        // initialze priority queue with singleton trees
        MinPQ<Node> pq = new MinPQ<>();
        for (char c = 0; c < R; c++)
            if (characterFreq.get(c) != null)
                pq.insert(new Node(characterFreq.get(c), c));

        // merge two smallest trees
        while (pq.size() > 1) {
            Node left = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node(left, right);
            pq.insert(parent);
        }
        return pq.delMin();
    }

    // write bitstring-encoded trie to standard output
    private static void writeTrie(Node x) {
        if (x.isLeaf()) {
            BinaryStdOut.write(true);
            BinaryStdOut.write(x.getCharacter(), 8);
            return;
        }
        BinaryStdOut.write(false);
        writeTrie(x.getLeft());
        writeTrie(x.getRight());
    }


=======
        return "";
    }

>>>>>>> parent of d0ed098... Progress
    /**
     * Returns the root of the compression tree.
     * @return the root of the compression tree.
     */
    Node getCompressionTree() {
        return null;
    }

    /**
     * Returns a Map<Character, String> with the character and the code that is used to encode it.
     * For "aba" this would result in: ['b' -> "0", 'a' -> "1"]
     * And for "cacbcac" this would result in: ['b' -> "00", 'a' -> "01", 'c' -> "1"]
     * @return the Huffman codes
     */
    Map<Character, String> getCodes() {
        return null;
    }

}
