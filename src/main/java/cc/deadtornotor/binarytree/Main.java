package cc.deadtornotor.binarytree;

import cc.deadtornotor.binarytree.benchmark.BenchmarkRunner;
import cc.deadtornotor.binarytree.benchmark.Result;
import cc.deadtornotor.binarytree.benchmark.adapter.ArrayListAdapter;
import cc.deadtornotor.binarytree.benchmark.adapter.BinaryTreeAdapter;
import cc.deadtornotor.binarytree.benchmark.adapter.HashSetAdapter;
import cc.deadtornotor.binarytree.benchmark.adapter.LinkedListAdapter;
import cc.deadtornotor.binarytree.cli.CLI;
import cc.deadtornotor.binarytree.cli.Color;
import cc.deadtornotor.binarytree.cli.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    static CLI cli = new CLI(Color.WHITE);
    static Menu menu = new Menu("Binary Tree Menu", cli);

    public static void main(String[] args) {
//        menu.add(1, "Insert random values", Main::insertRandom);
//        menu.add(2, "In-order traversal", Main::inOrder);
//        menu.add(3, "Check if value exists", Main::checkExists);
//        menu.add(4, "Pretty print tree", Main::printTree);
//        menu.add(5, "Balance tree", Main::balanceTree);
//        menu.add(6, "Find min value", Main::minValue);
//        menu.add(7, "Find max value", Main::maxValue);
//        menu.add(10, "Delete a value", Main::deleteValue);
//        menu.add(9, "Get height", Main::getHeight);
//        menu.add(8, "Get depth of value", Main::getDepth);
//        menu.add(11, "Count nodes", Main::countNodes);
//        menu.add(13, "Reset tree", Main::resetTree);
//        menu.add(12, "Function summary", Main::summary);
//
//        menu.loop();

        Random rand = new Random();
        List<Integer> benchmarkValues = new ArrayList<>();

        for (int i = 0; i < 100000; i++) {
            benchmarkValues.add(rand.nextInt());
        }

        Result binaryTreeResult = BenchmarkRunner.runBenchmark("BinaryTree", (BinaryTreeAdapter::new), benchmarkValues);
        Result arrayListResult = BenchmarkRunner.runBenchmark("ArrayList", (ArrayListAdapter::new), benchmarkValues);
        Result hashSetResult = BenchmarkRunner.runBenchmark("HashSet", (HashSetAdapter::new), benchmarkValues);
        Result linkedListResult = BenchmarkRunner.runBenchmark("LinkedList", (LinkedListAdapter::new), benchmarkValues);
    }
}
