package cc.deadtornotor.binarytree.benchmark.adapter;

import cc.deadtornotor.binarytree.benchmark.Benchmarkable;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LinkedListAdapter implements Benchmarkable {
    private final LinkedList<Integer> list = new LinkedList<>();

    @Override
    public boolean warmup() {
        return false;
    }

    @Override
    public boolean traverseInOrder() {
        List<Integer> sorted = new java.util.ArrayList<>(list);
        Collections.sort(sorted);

        int dummy = 0;
        for (Integer val : sorted) {
            dummy += val;
        }
        return dummy != 0;
    }

    @Override
    public void insert(int value) {
        list.add(value);
    }

    @Override
    public boolean search(int value) {
        return list.contains(value);
    }
}