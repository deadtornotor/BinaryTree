package cc.deadtornotor.binarytree.benchmark;

import java.util.List;
import java.util.function.Supplier;

public class BenchmarkRunner {

    private static final int RUN_COUNT = 50;

    public static Result runBenchmark(String name, Supplier<Benchmarkable> structureFactory, List<Integer> data) {
        long totalWarmupTime = 0;
        long totalInsertTime = 0;
        long totalSearchTime = 0;
        long totalTraverseTime = 0;

        for (int i = 0; i < RUN_COUNT; i++) {
            Benchmarkable structure = structureFactory.get();

            long start = System.nanoTime();
            for (int val : data) {
                structure.insert(val);
            }
            totalInsertTime += System.nanoTime() - start;

            start = System.nanoTime();
            boolean warmup = structure.warmup();
            long warmupTime = System.nanoTime() - start;
            if (warmup) {
                totalWarmupTime += warmupTime;
            }

            int target = data.get(data.size() / 2);
            start = System.nanoTime();
            structure.search(target);
            totalSearchTime += System.nanoTime() - start;

            start = System.nanoTime();
            boolean traverseInOrderSupported = structure.traverseInOrder();
            long traverseTime = System.nanoTime() - start;
            if (traverseInOrderSupported) {
                totalTraverseTime += traverseTime;
            }
        }

        Result averageResult = new Result(
            totalWarmupTime / RUN_COUNT,
            totalInsertTime / RUN_COUNT,
            totalSearchTime / RUN_COUNT,
            totalTraverseTime / RUN_COUNT
        );

        System.out.println(name + " (avg of " + RUN_COUNT + " runs) -> " + averageResult);
        return averageResult;
    }
}