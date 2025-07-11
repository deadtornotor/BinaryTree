package cc.deadtornotor.binarytree;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> {
    TreeNode<T> root;

    public BinaryTree() {
        root = null;
    }

    public void insert(T value) {
        root = insert(root, value);
    }

    public void delete(T value) {
        if (!exists(value)) {
            return;
        }

        root = delete(root, value);
    }

    public void inOrder() {
        inOrder(root);

        System.out.println();
    }

    public boolean exists(T value) {
        return exists(root, value);
    }

    public void printTree() {
        printTree(root, "", true);
    }

    public void balance() {
        List<T> sorted = new ArrayList<>();

        inOrder(root, sorted);

        root = buildBalancedNode(sorted, 0, sorted.size() -1);
    }

    public T minValue() {
        return value(root,true);
    }

    public T maxValue() {
        return value(root,false);
    }

    public int height() {
        return height(root);
    }

    public int depth(T value) {
        return depth(root, value, 0);
    }

    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }

        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    private int depth(TreeNode<T> node, T value, int depth) {
        if (node == null) {
            return -1;
        }

        if (value.equals(node.value)) {
            return depth;
        }

        if (value.compareTo(node.value) < 0) {
            return depth(node.left, value, depth + 1);
        }

        return depth(node.right, value, depth + 1);
    }

    private int height(TreeNode<T> node) {
        if (node == null) {
            return -1;
        }

        int maxHeight = Math.max(getHeight(node.left), getHeight(node.right));
        return 1 + maxHeight;
    }

    private TreeNode<T> buildBalancedNode(List<T> sorted, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;

        TreeNode<T> node = new TreeNode<>(sorted.get(mid));

        node.left = buildBalancedNode(sorted, start, mid - 1);
        node.right = buildBalancedNode(sorted, mid + 1, end);

        return node;
    }

    private TreeNode<T> insert(TreeNode<T> node, T value) {
        if (node == null) {
            return new TreeNode<T>(value);
        }

        if (value.compareTo(node.value) < 0) {
            node.left = insert(node.left, value);
        } else if(value.compareTo(node.value) > 0) {
            node.right = insert(node.right, value);
        }

        return node;
    }


    private void inOrder(TreeNode<T> node, List<T> set) {
        if (node == null) {
            return;
        }

        inOrder(node.left, set);
        set.add(node.value);
        inOrder(node.right, set);
    }

    private void inOrder(TreeNode<T> node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.print(node.value + " ");
        inOrder(node.right);
    }


    private boolean exists(TreeNode<T> node, T value) {
        if (node == null) {
            return false;
        }

        if(value.equals(node.value)) {
            return true;
        }

        if (value.compareTo(node.value) < 0) {
            return exists(node.left, value);
        } else {
            return exists(node.right, value);
        }
    }

    private void printTree(TreeNode<T> node, String prefix, boolean isTail) {
        if (node == null) return;

        if (node.right != null) {
            printTree(node.right, prefix + (isTail ? "│      " : "       "), false);
        }

        System.out.println(prefix + (isTail ? "└───── " : "┌───── " ) + node.value);

        if (node.left != null) {
            printTree(node.left, prefix + (isTail ? "       " : "│      "), true);
        }
    }

    private T value(TreeNode<T> node, boolean gotoNegative) {
        if (node == null) {
            return null;
        }

        if (gotoNegative) {
            if (node.left == null) {
                return node.value;
            }

            return value(node.left, gotoNegative);
        }

        if (node.right == null) {
            return node.value;
        }

        return value(node.right, gotoNegative);
    }

    private TreeNode<T> delete(TreeNode<T> node, T value) {
        if (node == null) {
            return null;
        }

        if (value.compareTo(node.value) < 0) {
            node.left = delete(node.left, value);
            return node;
        } else if (value.compareTo(node.value) > 0) {
            node.right = delete(node.right, value);
            return node;
        }

        // if one or both child nodes are null
        if (node.left == null) {
            return node.right;
        }
        else if (node.right == null) {
            return node.left;
        }


        T minValue = findMin(node.right);
        node.value = minValue;
        node.right = delete(node.right, minValue);

        return node;
    }

    private T findMin(TreeNode<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.value;
    }
}
