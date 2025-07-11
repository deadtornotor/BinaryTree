package cc.deadtornotor.binarytree;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static BinaryTree<Integer> tree = new BinaryTree<Integer>();
    static Random random = new Random();
    static Menu menu = new Menu("Binary Tree Menu");
    static boolean exit = false;

    private static final int MAX_VALUE = 500;
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        menu.add(1, "Insert random values", Main::insertRandom);
        menu.add(2, "In-order traversal", Main::inOrder);
        menu.add(3, "Check if value exists", Main::checkExists);
        menu.add(4, "Pretty print tree", Main::printTree);
        menu.add(5, "Balance tree", Main::balanceTree);
        menu.add(6, "Find min value", Main::minValue);
        menu.add(7, "Find max value", Main::maxValue);
        menu.add(8, "Delete a value", Main::deleteValue);
        menu.add(9, "Get height", Main::getHeight);
        menu.add(10, "Get depth of value", Main::getDepth);
        menu.add(11, "Count nodes", Main::countNodes);
        menu.add(12, "Reset tree", Main::resetTree);
        menu.add(13, "Function summary", Main::summary);
        menu.add(0, "Exit", Main::exit);

        while (true) {
            menu.print();

            System.out.print(System.getProperty("user.name") + "@binarytree ~/ $ ");
            int choice = scanner.nextInt();

            if (!menu.run(choice)) {
                System.out.println("Invalid choice.");
            }
            
            if (exit) {
                System.out.println("Exiting...");
                break;
            }
        }
    }

    private static void exit() {
        exit = true;
    }

    private static void insertRandom() {
        System.out.print("How many values to insert? ");
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int val = random.nextInt(5000);
            tree.insert(val);
            System.out.println("Inserted: " + val);
        }
    }

    private static void inOrder() {
        System.out.println("In-order:");
        tree.inOrder();
    }

    private static void checkExists() {
        System.out.print("Enter value to check: ");
        int val = scanner.nextInt();
        System.out.println("Exists? " + tree.exists(val));
    }

    private static void printTree() {
        System.out.println("Pretty tree:");
        tree.printTree();
    }

    private static void balanceTree() {
        tree.balance();
        System.out.println("Tree balanced.");
    }

    private static void minValue() {
        System.out.println("Min value: " + tree.minValue());
    }

    private static void maxValue() {
        System.out.println("Max value: " + tree.maxValue());
    }

    private static void deleteValue() {
        System.out.print("Enter value to delete: ");
        int val = scanner.nextInt();
        tree.delete(val);
        System.out.println("Deleted (if existed): " + val);
    }

    private static void getHeight() {
        System.out.println("Tree height: " + tree.height());
    }

    private static void getDepth() {
        System.out.print("Enter value to find depth: ");
        int val = scanner.nextInt();
        int depth = tree.depth(val);
        if (depth == -1) System.out.println("Not found.");
        else System.out.println("Depth: " + depth);
    }

    private static void countNodes() {
        System.out.println("Node count: " + tree.countNodes());
    }

    private static void resetTree() {
        tree = new BinaryTree<>();
        System.out.println("Tree reset.");
    }

    private static void summary() {
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        Random random = new Random();
        
        // Random node count
        int nodeCount = 10;
        System.out.println(ANSI_YELLOW + "Generating binary tree with " + nodeCount + " nodes" + ANSI_RESET);

        // Insert random values
        System.out.print(ANSI_BLUE + "Inserting values: " + ANSI_RESET);
        tree.insert(random.nextInt(100) + 50);
        for (int i = 0; i < nodeCount - 1; i++) {
            int value = random.nextInt(MAX_VALUE);
            System.out.print(ANSI_GREEN + value + " " + ANSI_RESET);
            tree.insert(value);
        }
        System.out.println();

        // Initial tree stats
        System.out.println(ANSI_YELLOW + "\n=== Initial Tree Stats ===" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "In-order traversal:" + ANSI_RESET);
        tree.inOrder();
        System.out.println(ANSI_BLUE + "\nTree structure:" + ANSI_RESET);
        tree.printTree();
        System.out.println(ANSI_BLUE + "Height: " + tree.height() + ANSI_RESET);

        // Balance the tree
        System.out.println(ANSI_RED + "\nBalancing the tree" + ANSI_RESET);
        tree.balance();
        System.out.println(ANSI_BLUE + "Balanced tree structure:" + ANSI_RESET);
        tree.printTree();
        System.out.println(ANSI_BLUE + "Height after balancing: " + tree.height() + ANSI_RESET);

        // Multiple random searches
        int searchCount = 10;
        System.out.println(ANSI_YELLOW + "\nPerforming " + searchCount + " random searches" + ANSI_RESET);
        for (int i = 0; i < searchCount; i++) {
            int searchKey = random.nextInt(MAX_VALUE);
            System.out.println(ANSI_GREEN + "Searching for " + searchKey + ": " + 
                (tree.exists(searchKey) ? "Found" : "Not found") + ANSI_RESET);
        }

        // Multiple random duplicate insertions
        int insertCount = 5;
        System.out.println(ANSI_YELLOW + "\nInserting " + insertCount + " random duplicate values" + ANSI_RESET);
        for (int i = 0; i < insertCount; i++) {
            int randomValue = random.nextInt(MAX_VALUE);
            System.out.print(ANSI_GREEN + randomValue + " " + ANSI_RESET);
            tree.insert(randomValue);
        }
        System.out.println();
        System.out.println(ANSI_BLUE + "In-order after duplicate insertions:" + ANSI_RESET);
        tree.inOrder();

        // Show min and max
        System.out.println(ANSI_YELLOW + "\nTree Extremes" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Minimum value: " + tree.minValue() + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Maximum value: " + tree.maxValue() + ANSI_RESET);

        // Multiple random deletions
        int deleteCount = 5;
        System.out.println(ANSI_YELLOW + "\nPerforming " + deleteCount + " random deletions" + ANSI_RESET);
        for (int i = 0; i < deleteCount; i++) {
            int deleteKey = random.nextInt(MAX_VALUE);
            System.out.println(ANSI_RED + "Deleting key " + deleteKey + ANSI_RESET);
            tree.delete(deleteKey);
        }
        System.out.println(ANSI_BLUE + "In-order after deletions:" + ANSI_RESET);
        tree.inOrder();

        // Try deleting a non-existent key
        int nonExistentKey = MAX_VALUE + random.nextInt(100);
        System.out.println(ANSI_YELLOW + "\nAttempting to delete non-existent key " + nonExistentKey + ANSI_RESET);
        tree.delete(nonExistentKey);
        System.out.println(ANSI_BLUE + "In-order after attempt:" + ANSI_RESET);
        tree.inOrder();

        // Final tree stats
        System.out.println(ANSI_YELLOW + "\n=== Final Tree Stats ===" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "Final height: " + tree.height() + ANSI_RESET);
        System.out.println(ANSI_BLUE + "Final structure:" + ANSI_RESET);
        tree.printTree();
    }
}
