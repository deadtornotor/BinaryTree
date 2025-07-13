package cc.deadtornotor.binarytree.benchmark.adapter;

import cc.deadtornotor.binarytree.BinaryTree;
import cc.deadtornotor.binarytree.benchmark.Benchmarkable;

public class BinaryTreeAdapter implements Benchmarkable {
    private final BinaryTree<Integer> tree = new BinaryTree<>();

    @Override
    public boolean warmup() {
        tree.balance();
        return true;
    }

    @Override
    public void insert(int value) {
        tree.insert(value);
    }

    @Override
    public boolean search(int value) {
        return tree.exists(value);
    }

    @Override
    public boolean traverseInOrder() {
        int dummy = 0;
        for (Integer val : tree) {
            dummy += val;
        }
        return dummy != 0;
    }
}
