package se.fnord.jmh.optionalEquals;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import se.fnord.jmh.optionalEquals.TestData.Pair;

import java.util.concurrent.TimeUnit;

public class OptionalEqualsBenchmark {
    public static final int COUNT = 10000;

    @State(Scope.Benchmark)
    public static class Data {
        Pair[] instances;

        @Setup
        public void setup() {
            instances = TestData.createTestData(COUNT, OptionalEquals::new);
        }
    }

    @Benchmark
    @Fork(4)
    @Warmup(iterations = 10)
    @Measurement(iterations = 10)
    @OperationsPerInvocation(COUNT)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void benchmarkEquals(Blackhole bh, Data data) {
        for (Pair p: data.instances) {
            bh.consume(p.first.equals(p.second));
        }
    }
}
