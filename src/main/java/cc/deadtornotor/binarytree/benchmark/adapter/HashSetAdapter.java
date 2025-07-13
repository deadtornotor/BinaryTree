package cc.deadtornotor.binarytree.benchmark.adapter;

import cc.deadtornotor.binarytree.benchmark.Benchmarkable;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class HashSetAdapter implements Benchmarkable {
    private final HashSet<Integer> set = new HashSet<>();

    @Override
    public boolean warmup() {
        return false;
    }

    @Override
    public boolean traverseInOrder() {
        List<Integer> sorted = new java.util.ArrayList<>(set);
        Collections.sort(sorted);

        int dummy = 0;
        for (Integer val : sorted) {
            dummy += val;
        }
        return dummy != 0;
    }

    @Override
    public void insert(int value) {
        set.add(value);
    }

    @Override
    public boolean search(int value) {
        return set.contains(value);
    }
}