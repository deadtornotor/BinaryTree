package cc.deadtornotor.binarytree.benchmark;

public interface Benchmarkable {
    void insert(int value);
    boolean search(int value);
    boolean warmup();
    boolean traverseInOrder();
}