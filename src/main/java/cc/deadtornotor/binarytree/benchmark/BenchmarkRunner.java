package cc.deadtornotor.binarytree.benchmark;

import java.util.List;
import java.util.function.Supplier;

public class BenchmarkRunner {

    public static Result runBenchmark(String name, Supplier<Benchmarkable> structureFactory, List<Integer> data, int runCount) {
        long totalWarmupTime = 0;
        long totalInsertTime = 0;
        long totalSearchTime = 0;
        long totalTraverseTime = 0;

        for (int i = 0; i < runCount; i++) {
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

        return new Result(
            totalWarmupTime / runCount,
            totalInsertTime / runCount,
            totalSearchTime / runCount,
            totalTraverseTime / runCount
        );
    }
}