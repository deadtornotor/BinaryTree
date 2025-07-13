package cc.deadtornotor.binarytree.benchmark;

public class Result {
    private final long warmupTimeNs;
    private final long insertTimeNs;
    private final long searchTimeNs;
    private final long traverseInOrderTimeNs;

    public Result(long warmupTimeNs, long insertTimeNs, long searchTimeNs, long traverseInOrderTimeNs) {
        this.warmupTimeNs = warmupTimeNs;
        this.insertTimeNs = insertTimeNs;
        this.searchTimeNs = searchTimeNs;
        this.traverseInOrderTimeNs = traverseInOrderTimeNs;
    }

    public long warmupTimeNs() {
        return warmupTimeNs;
    }

    public long insertTimeNs() {
        return insertTimeNs;
    }

    public long searchTimeNs() {
        return searchTimeNs;
    }

    public long traverseInOrderTimeNs() {
        return traverseInOrderTimeNs;
    }

    public double warmupTimeMs() {
        return warmupTimeNs / 1_000_000.0;
    }

    public double insertTimeMs() {
        return insertTimeNs / 1_000_000.0;
    }

    public double searchTimeMs() {
        return searchTimeNs / 1_000_000.0;
    }

    public double traverseInOrderTimeMs() {
        return traverseInOrderTimeNs / 1_000_000.0;
    }
}
