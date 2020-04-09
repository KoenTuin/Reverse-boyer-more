package nl.hva.ict.se.sands;

import huffmanClasses.BinaryStdIn;
import huffmanClasses.BinaryStdOut;
import huffmanClasses.MinPQ;

import java.awt.datatransfer.SystemFlavorMap;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HuffmanCompression {
    private final String text;
    // alphabet size of extended ASCII
    private static final int R = 256;
    private Node root;
    private final Map<Character, String> map;
    private int[] freq;

    // Huffman trie node
//    private static class Node implements Comparable<Node> {
//        private final char ch;
//        private final int freq;
//        private final Node left, right;
//
//        Node(char ch, int freq, Node left, Node right) {
//            this.ch    = ch;
//            this.freq  = freq;
//            this.left  = left;
//            this.right = right;
//        }
//
//        // is the node a leaf node?
//        private boolean isLeaf() {
//            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
//            return (left == null) && (right == null);
//        }
//
//        // compare, based on frequency
//        public int compareTo(Node that) {
//            return this.freq - that.freq;
//        }
//    }

    public HuffmanCompression(String text) {
        this.text = text;
        map = new HashMap<>();
        compress();
    }

    public HuffmanCompression(InputStream input) {
        Scanner sc = new Scanner(input);
        sc.useDelimiter("\\Z"); // EOF marker
        text = sc.next();
        map = new HashMap<>();
        compress();
    }

    /**
     * Returns the compression ratio assuming that every characters in the text uses 8 bits.
     *
     * @return the compression ratio.
     */
    public double getCompressionRatio() {
        return 0.0;
    }

    /**
     * Compresses the text that was provided to the constructor.
     *
     * @return
     */
    public String compress() {
        // read the input
        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();

        // tabulate frequency counts
        freq = new int[R];
        for (int i = 0; i < input.length; i++)
            freq[input[i]]++;

        // build Huffman trie
        root = buildTrie();
//         build code table
        String[] st = new String[R];
        buildCode(st, root, "");
        for (int i = 0; i < st.length; i++) {
            if (st[i] != null) {
                map.put((char) i, st[i]);
            }
        }


        // print trie for decoder
        writeTrie(root);

        // print number of bytes in original uncompressed message

        BinaryStdOut.write(input.length);

        // use Huffman code to encode input
        for (int i = 0; i < input.length; i++) {
            String code = st[input[i]];
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(j) == '0') {
                    BinaryStdOut.write(false);
                } else if (code.charAt(j) == '1') {
                    BinaryStdOut.write(true);
                } else throw new IllegalStateException("Illegal state");
            }
        }
        String result = BinaryStdIn.readString();
        // close output stream
        BinaryStdOut.close();

        return result;
    }

    // build the Huffman trie given frequencies
    private Node buildTrie() {

        // initialze priority queue with singleton trees
        MinPQ<Node> pq = new MinPQ<>();
        for (char c = 0; c < R; c++)
            if (freq[c] > 0)
                pq.insert(new Node(freq[c], c));

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


    /**
     * Returns the root of the compression tree.
     *
     * @return the root of the compression tree.
     */
    Node getCompressionTree() {
        return root;
    }

    /**
     * Returns a Map<Character, String> with the character and the code that is used to encode it.
     * For "aba" this would result in: ['b' -> "0", 'a' -> "1"]
     * And for "cacbcac" this would result in: ['b' -> "00", 'a' -> "01", 'c' -> "1"]
     *
     * @return the Huffman codes
     */
    Map<Character, String> getCodes() {
//        Map<Character, String> map = new HashMap<>();
//
//        String[] keyCount = new String[R];
//        buildCode(keyCount, root, "");

        return map;
    }

    // make a lookup table from symbols and their encodings
    private static void buildCode(String[] st, Node x, String s) {
//        if (x == null) {
//            return;
//        }

        if (!x.isLeaf()) {
            buildCode(st, x.getLeft(), s + '0');
            buildCode(st, x.getRight(), s + '1');
        } else {
            st[x.getCharacter()] = s;
        }
    }

}
