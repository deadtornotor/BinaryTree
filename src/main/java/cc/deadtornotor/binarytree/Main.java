package cc.deadtornotor.binarytree;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static BinaryTree<Integer> tree = new BinaryTree();
    static Random random = new Random();

    public static void main(String[] args) {
        Map<Integer, Runnable> menu = new HashMap<>();

        menu.put(1, Main::insertRandom);
        menu.put(2, Main::inOrder);
        menu.put(3, Main::checkExists);
        menu.put(4, Main::printTree);
        menu.put(5, Main::balanceTree);
        menu.put(6, Main::minValue);
        menu.put(7, Main::maxValue);
        menu.put(8, Main::deleteValue);
        menu.put(9, Main::getHeight);
        menu.put(10, Main::getDepth);
        menu.put(11, Main::countNodes);
        menu.put(12, Main::resetTree);

        while (true) {
            System.out.println("\n--- Binary Tree Menu ---");
            System.out.println("1. Insert random values");
            System.out.println("2. In-order traversal");
            System.out.println("3. Check if value exists");
            System.out.println("4. Pretty print tree");
            System.out.println("5. Balance tree");
            System.out.println("6. Find min value");
            System.out.println("7. Find max value");
            System.out.println("8. Delete a value");
            System.out.println("9. Get height");
            System.out.println("10. Get depth of value");
            System.out.println("11. Count nodes");
            System.out.println("12. Reset tree");
            System.out.println("0. Exit");

            System.out.print("Choose option: ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("Exiting.");
                break;
            }

            Runnable action = menu.get(choice);
            if (action != null) {
                action.run();
            } else {
                System.out.println("Invalid choice.");
            }
        }
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
        System.out.println("Tree height: " + tree.getHeight());
    }

    private static void getDepth() {
        System.out.print("Enter value to find depth: ");
        int val = scanner.nextInt();
        int depth = tree.getDepth(val);
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
}
