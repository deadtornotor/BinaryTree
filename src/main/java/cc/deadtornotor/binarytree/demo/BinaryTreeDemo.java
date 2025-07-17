package cc.deadtornotor.binarytree.demo;

import cc.deadtornotor.binarytree.BinaryTree;
import cc.deadtornotor.binarytree.benchmark.BenchmarkRunner;
import cc.deadtornotor.binarytree.benchmark.Result;
import cc.deadtornotor.binarytree.benchmark.adapter.ArrayListAdapter;
import cc.deadtornotor.binarytree.benchmark.adapter.BinaryTreeAdapter;
import cc.deadtornotor.binarytree.benchmark.adapter.HashSetAdapter;
import cc.deadtornotor.binarytree.benchmark.adapter.LinkedListAdapter;
import cc.deadtornotor.binarytree.cli.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinaryTreeDemo {
    private final CLI cli;
    private final Menu menu;
    private final BinaryTree<Integer> tree = new BinaryTree<>();

    public BinaryTreeDemo() {
        this.cli = CLI.getInstance();
        this.menu = new Menu("Binary Tree Demo");

        menu.add(1, new MenuAction(
                "Insert random values",
                this,
                "insertRandom",
                true,
                new MethodParameter("Amount", int.class),
                new MethodParameter("Max value", int.class),
                new MethodParameter("Min value", int.class))
        );
        menu.add(2, new MenuAction("Display tree", this, "printTree", true));
        menu.add(3, new MenuAction("Balance tree", this, "balanceTree", true));
        menu.add(4, new MenuAction(
                "Insert value",
                this,
                "insertValue",
                true,
                new MethodParameter("Value", int.class))
        );
        menu.add(5, new MenuAction(
                "Delete value",
                this,
                "deleteValue",
                true,
                new MethodParameter("Value", int.class))
        );
        menu.add(6, new MenuAction(
                "Check if value exists",
                this,
                "checkExists",
                true,
                new MethodParameter("Value", int.class))
        );
        menu.add(7, new MenuAction("Find minimum value", this, "findMin", true));
        menu.add(8, new MenuAction("Find maximum value", this, "findMax", true));
        menu.add(9, new MenuAction("Get tree height", this, "getTreeHeight", true));
        menu.add(10, new MenuAction(
                "Get depth of value",
                this,
                "getDepth",
                true,
                new MethodParameter("Value", int.class))
        );
        menu.add(11, new MenuAction("Count nodes", this, "countNodes", true));
        menu.add(12, new MenuAction("Clear tree", this, "clearTree", true));
        menu.add(13, new MenuAction(
                "Benchmark data structures",
                this,
                "benchmark",
                true,
                new MethodParameter("Number of values", int.class),
                new MethodParameter("Number of runs", int.class))
        );
    }

    public void run() {
        menu.loop();
    }

    public void insertRandom(int amount, int maxValue, int minValue) {
        if (amount <= 0 || maxValue < minValue) {
            cli.error("Invalid values");
            return;
        }

        Random random = new Random();

        for (int i = 0; i < amount; i++) {
            int value = random.nextInt((maxValue - minValue) + 1) + minValue;
            tree.insert(value);
        }

        cli.success("Inserted " + amount + " random values between " + minValue + " and " + maxValue);
    }

    public void printTree() {
        if (tree.countNodes() == 0) {
            cli.error("Tree is empty");
            return;
        }
        tree.printTree();
    }

    public void balanceTree() {
        if (tree.countNodes() == 0) {
            cli.error("Tree is empty");
            return;
        }
        tree.balance();
        cli.success("Balanced tree");
    }

    public void insertValue(int value) {
        tree.insert(value);
        cli.success("Inserted value " + value);
    }

    public void deleteValue(int value) {
        if (!tree.exists(value)) {
            cli.error("Value " + value + " not found in tree");
            return;
        }
        tree.delete(value);
        cli.success("Deleted value " + value);
    }

    public void checkExists(int value) {
        if (tree.exists(value)) {
            cli.success("Value " + value + " exists in the tree");
        } else {
            cli.error("Value " + value + " does not exist in the tree");
        }
    }

    public void findMin() {
        Integer min = tree.minValue();
        if (min == null) {
            cli.error("Tree is empty");
            return;
        }
        cli.success("Minimum value: " + min);
    }

    public void findMax() {
        Integer max = tree.maxValue();
        if (max == null) {
            cli.error("Tree is empty");
            return;
        }
        cli.success("Maximum value: " + max);
    }

    public void getTreeHeight() {
        int height = tree.height();
        if (height == -1) {
            cli.error("Tree is empty");
            return;
        }
        cli.success("Tree height: " + height);
    }

    public void getDepth(int value) {
        int depth = tree.depth(value);
        if (depth == -1) {
            cli.error("Value " + value + " not found in tree");
            return;
        }
        cli.success("Depth of value " + value + ": " + depth);
    }

    public void countNodes() {
        int count = tree.countNodes();
        cli.success("Number of nodes: " + count);
    }

    public void clearTree() {
        tree.clear();
        cli.success("Tree cleared");
    }

    public void benchmark(int valueAmount, int runCount) {
        if (valueAmount <= 0 || runCount <= 0) {
            cli.error("Invalid input: Number of values and runs must be positive");
            return;
        }

        Random rand = new Random();
        List<Integer> benchmarkValues = new ArrayList<>();
        for (int i = 0; i < valueAmount; i++) {
            benchmarkValues.add(rand.nextInt());
        }

        Result binaryTreeResult = BenchmarkRunner.runBenchmark("BinaryTree", (BinaryTreeAdapter::new), benchmarkValues, runCount);
        Result arrayListResult = BenchmarkRunner.runBenchmark("ArrayList", (ArrayListAdapter::new), benchmarkValues, runCount);
        Result hashSetResult = BenchmarkRunner.runBenchmark("HashSet", (HashSetAdapter::new), benchmarkValues, runCount);
        Result linkedListResult = BenchmarkRunner.runBenchmark("LinkedList", (LinkedListAdapter::new), benchmarkValues, runCount);

        cli.borderedPrintln("Benchmark Results (" + valueAmount + " values, " + runCount + " runs)", Color.CYAN, null, Color.YELLOW);
        cli.println();

        String[] headers = {"Structure", "Insert (ms)", "Warmup (ms)", "Search (ms)", "Traverse (ms)"};
        int[] columnWidths = {15, 12, 12, 12, 14};
        String hLine = "┃" + "━".repeat(columnWidths[0]) + "┳" + "━".repeat(columnWidths[1]) + "┳" +
                "━".repeat(columnWidths[2]) + "┳" + "━".repeat(columnWidths[3]) + "┳" +
                "━".repeat(columnWidths[4]) + "┃";

        cli.println(hLine, Color.YELLOW);
        StringBuilder headerRow = new StringBuilder("┃");
        for (int i = 0; i < headers.length; i++) {
            headerRow.append(String.format(" %-" + (columnWidths[i] - 1) + "s", headers[i])).append("┃");
        }
        cli.println(headerRow.toString(), Color.CYAN);
        cli.println(hLine, Color.YELLOW);

        printResultRow("BinaryTree", binaryTreeResult, columnWidths);
        printResultRow("ArrayList", arrayListResult, columnWidths);
        printResultRow("HashSet", hashSetResult, columnWidths);
        printResultRow("LinkedList", linkedListResult, columnWidths);

        cli.println(hLine.replace('┳', '┻'), Color.YELLOW);
        cli.success("Benchmark completed for " + valueAmount + " values and " + runCount + " runs");
    }

    private void printResultRow(String name, Result result, int[] columnWidths) {
        String row = String.format(
                "┃ %-" +
                (columnWidths[0] - 1) +
                "s┃ %-" + (columnWidths[1] - 1) +
                ".3f┃ %-" +
                (columnWidths[2] - 1) + ".3f┃ %-" +
                (columnWidths[3] - 1) + ".3f┃ %-" +
                (columnWidths[4] - 1) + ".3f┃",
            name,
            result.insertTimeMs(),
            result.warmupTimeMs(),
            result.searchTimeMs(),
            result.traverseInOrderTimeMs()
        );
        cli.println(row, Color.GREEN);
    }
}