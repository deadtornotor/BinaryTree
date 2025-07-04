package cc.deadtornotor.binarytree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    BinaryNode root;

    public BinaryTree() {
        root = null;
    }

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private BinaryNode insertRec(BinaryNode root, int key) {
        if (root == null) {
            root = new BinaryNode(key);
            return root;
        }

        if (key < root.value) {
            root.left = insertRec(root.left, key);
        } else if(key > root.value) {
            root.right = insertRec(root.right, key);
        }

        return root;
    }

    public void inOrder() {
        inOrderRec(root);

        System.out.println();System.out.println();

    }

    private void inOrder(BinaryNode root, List<Integer> set) {
        if (root == null) {
            return;
        }

        inOrder(root.left, set);
        set.add(root.value);
        inOrder(root.right, set);
    }

    private void inOrderRec(BinaryNode root) {
        if (root == null) {
            return;
        }

        inOrderRec(root.left);
        System.out.print(root.value + " ");
        inOrderRec(root.right);
    }

    public boolean search(int key) {
        return searchRec(root, key);
    }

    private boolean searchRec(BinaryNode root, int key) {
        if (root == null) {
            return false;
        }

        if(root.value == key) {
            return true;
        }

        if (key < root.value) {
            return searchRec(root.left, key);
        } else {
            return searchRec(root.right, key);
        }
    }

    public void printTree() {
        printTree(root, "", true);
    }

    private void printTree(BinaryNode root, String prefix, boolean isTail) {
        if (root == null) return;

        if (root.right != null) {
            printTree(root.right, prefix + (isTail ? "│      " : "       "), false);
        }

        System.out.println(prefix + (isTail ? "└───── " : "┌───── " ) + root.value);

        if (root.left != null) {
            printTree(root.left, prefix + (isTail ? "       " : "│      "), true);
        }
    }

    public void balance() {
        List<Integer> sorted = new ArrayList<>();

        inOrder(root, sorted);

        root = buildBalancedNode(sorted, 0, sorted.size() -1);
    }

    private BinaryNode buildBalancedNode(List<Integer> sorted, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;

        BinaryNode node = new BinaryNode(sorted.get(mid));

        node.left = buildBalancedNode(sorted, start, mid - 1);
        node.right = buildBalancedNode(sorted, mid + 1, end);

        return node;
    }
}
