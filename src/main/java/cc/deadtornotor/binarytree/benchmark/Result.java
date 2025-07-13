package cc.deadtornotor.binarytree.benchmark;

public record Result(long warmupTimeNs, long insertTimeNs, long searchTimeNs, long traverseInOrderTimeNs) {
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
