package nl.hva.ict.se.sands;

import huffmanClasses.BinaryStdIn;
import huffmanClasses.BinaryStdOut;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.util.*;

public class HuffmanCompression {
    private final String text;
    // alphabet size of extended ASCII
    private static final int R = 256;
    private Node root;
    private final HashMap<Character, String> map;
    private final HashMap<Character, Integer> frequency = new HashMap<>();
    private char[] characters;
    private int[] freq;
    private StringBuilder bitOutput = new StringBuilder("");


    public HuffmanCompression(String text) {
        this.text = text;
        map = new HashMap<>();
        characters = this.text.toCharArray();
        compress();
    }

    public HuffmanCompression(InputStream input) {
        Scanner sc = new Scanner(input);
        sc.useDelimiter("\\Z"); // EOF marker
        text = sc.next();
        characters = this.text.toCharArray();
        map = new HashMap<>();
        compress();

    }

    /**
     * Returns the compression ratio assuming that every characters in the text uses 8 bits.
     * @return the compression ratio.
     */
    public double getCompressionRatio() {
        int totalOriginaText;
        int totalCompressedText;
        totalCompressedText = bitOutput.toString().length();
        totalOriginaText = characters.length * 8;
        System.out.println("bitOutput = " + bitOutput.toString());
        System.out.println(totalCompressedText + " : " + totalOriginaText);

        return 0.0;
    }

    /**
     * Compresses the text that was provided to the constructor.
     * @return
     */
    public String compress() {
        // read the input
        // compress -> text
        // text. each character count
        // if text.character doesnt exist in map create
        // if text.character exists increment

        for (char c : characters) {
            if(frequency.get(c) == null){
                frequency.put(c, 1);
                map.put(c,  "");
            }else{
                frequency.put(c , frequency.get(c)+1);
            }
        }

//        //ToDo build Huffman trie
        root = buildTrie();
//
//        //ToDo build code table
        buildCode(getCompressionTree(), "");

        // print trie for decoder
        writeTrie(root);

//        // print number of bytes in original uncompressed message
//
//        BinaryStdOut.write(input.length);
//
//        // use Huffman code to encode input
//        for (int i = 0; i < input.length; i++) {
//            String code = st[input[i]];
//            for (int j = 0; j < code.length(); j++) {
//                if (code.charAt(j) == '0') {
//                    BinaryStdOut.write(false);
//                } else if (code.charAt(j) == '1') {
//                    BinaryStdOut.write(true);
//                } else throw new IllegalStateException("Illegal state");
//            }
//        }
//        System.out.println("reading");
//        String result = BinaryStdIn.readString();
//        System.out.println("Finished reading");
//        // close output stream
//        BinaryStdOut.close();

        return null;
    }

    private Node buildTrie() {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequency.entrySet()  ) {
            if (entry.getValue() > 0 && entry.getKey() != null){
                pq.add(new Node(entry.getValue(), entry.getKey()));
            }
        }


        // special case in case there is only one character with a nonzero frequency
        if (pq.size() == 1) {
            if (frequency.get('\0') == 0) {
                pq.add(new Node(0, '\0'));
            } else{
                pq.add(new Node(0,'\1'));
            }
        }

        // merge two smallest trees
        while (pq.size() > 1) {
            Node left  = pq.poll();
            Node right = pq.poll();
            Node parent = new Node(left, right);
            pq.add(parent);
        }
        return pq.poll();
    }

    // write bitstring-encoded trie to standard output
    private void writeTrie(Node x) {

        if (x.isLeaf()) {
            BinaryStdOut.write(true);
            BinaryStdOut.write(x.getCharacter(), 8);
            return;
        }
        BinaryStdOut.write(false);
        writeTrie(x.getLeft());
        writeTrie(x.getRight());
    }

        //        if (x.isLeaf()) {
////            System.out.println("Bits: " + map.get(x));
//            bitOutput += map.get(x);
//            return;
//        }
//        writeTrie(x.getLeft());
//        writeTrie(x.getRight());
//        }

    /**
     * Returns the root of the compression tree.
     * @return the root of the compression tree.
     */
    Node getCompressionTree() {
        return root;
    }

    /**
     * Returns a Map<Character, String> with the character and the code that is used to encode it.
     * For "aba" this would result in: ['b' -> "0", 'a' -> "1"]
     * And for "cacbcac" this would result in: ['b' -> "00", 'a' -> "01", 'c' -> "1"]
     * @return the Huffman codes
     */
    Map<Character, String> getCodes() {
        return map;
    }

    // make a lookup table from symbols and their encodings
    private void buildCode(Node x, String s) {
        if (!x.isLeaf()) {
            buildCode(x.getLeft(), s + '0');
            buildCode(x.getRight(), s + '1');
        } else {
            map.put(x.getCharacter(), s);
            bitOutput.append(s);
//            bitOutput += s;
        }
        if (x.isLeaf()) {
//            bitOutput += s;
            System.out.println(x.getCharacter() + " - " + s +"BitOutput: "+ bitOutput);

        }
    }

}
