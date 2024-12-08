import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

// Node class for the Huffman Tree
class Node {
    char symbol;
    int frequency;
    Node left, right;

    Node(char symbol, int frequency) {
        this.symbol = symbol;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    // Constructor for internal nodes (non-leaf nodes)
    Node(int frequency, Node left, Node right) {
        this.symbol = '\0'; // Internal nodes do not represent symbols
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
}

// Custom comparator for the priority queue
class NodeComparator implements java.util.Comparator<Node> {
    public int compare(Node n1, Node n2) {
        return n1.frequency - n2.frequency; // Min-heap based on frequency
    }
}

public class GreedyHuffman {

    // Method to generate the Huffman Tree
    public Node buildHuffmanTree(Map<Character, Integer> frequencyMap) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>(new NodeComparator());

        // Create a leaf node for each symbol and add it to the heap
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            minHeap.add(node);
        }

        // Iterate until the heap contains only one node (the root of the Huffman Tree)
        while (minHeap.size() > 1) {
            // Extract the two nodes with the lowest frequency
            Node left = minHeap.poll();
            Node right = minHeap.poll();

            // Create a new internal node with the sum of the two frequencies
            Node internalNode = new Node(left.frequency + right.frequency, left, right);

            // Add the new node back to the heap
            minHeap.add(internalNode);
        }

        // The remaining node is the root of the Huffman Tree
        return minHeap.poll();
    }

    // Method to print Huffman Codes
    public void printHuffmanCodes(Node root, String code, Map<Character, String> huffmanCodeMap) {
        if (root == null) {
            return;
        }

        // If it's a leaf node, print the symbol and its code
        if (root.left == null && root.right == null) {
            huffmanCodeMap.put(root.symbol, code);
            System.out.println(root.symbol + ": " + code);
        }

        // Traverse the left subtree with '0' and the right subtree with '1'
        printHuffmanCodes(root.left, code + "0", huffmanCodeMap);
        printHuffmanCodes(root.right, code + "1", huffmanCodeMap);
    }

    // Method to build frequency map from input string
    public Map<Character, Integer> buildFrequencyMap(String text) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        return frequencyMap;
    }

    // Method to generate a random string of given size
    public String generateRandomString(int size) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(size);

        // Randomly pick characters from the lowercase alphabet
        for (int i = 0; i < size; i++) {
            char randomChar = (char) ('a' + random.nextInt(26)); // Random character between 'a' and 'z'
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Scanner object for user input

        // Get array size from the user
        System.out.print("Enter the size of the input string: ");
        int n = scanner.nextInt();  // Read user input for the size of the random string

        GreedyHuffman greedyHuffman = new GreedyHuffman();

        // Step 1: Generate a random string of size n
        String randomText = greedyHuffman.generateRandomString(n);

        // Step 2: Build the frequency map from the generated random string
        Map<Character, Integer> frequencyMap = greedyHuffman.buildFrequencyMap(randomText);

        // Step 3: Measure the time taken to build the Huffman Tree
        long start = System.nanoTime();
        Node root = greedyHuffman.buildHuffmanTree(frequencyMap);
        long end = System.nanoTime();

        long elapsedTimeInNanoseconds = end - start;
        double elapsedTimeInMilliseconds = elapsedTimeInNanoseconds / 1_000_000.0;

        // Step 4: Generate and print Huffman Codes
        Map<Character, String> huffmanCodeMap = new HashMap<>();
        System.out.println("\nHuffman Codes:");
        greedyHuffman.printHuffmanCodes(root, "", huffmanCodeMap);

        // Print the original string and encoded string
        StringBuilder encodedString = new StringBuilder();
        for (char c : randomText.toCharArray()) {
            encodedString.append(huffmanCodeMap.get(c));
        }

        System.out.println("\nOriginal String: " + randomText);
        System.out.println("Encoded String: " + encodedString);
        System.out.println("Time taken to build Huffman Tree: " + elapsedTimeInMilliseconds + " milliseconds");

        scanner.close();  // Close the scanner object
    }
}
