package dev.anthonychwong;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

public class StringInterpolationJavaBenchmark {
    public static void main(String[] args) throws Throwable {
        Options opt = new OptionsBuilder()
                .include(StringInterpolationJavaBenchmark.class.getSimpleName())
                .warmupIterations(3)
                .measurementIterations(2).measurementTime(TimeValue.valueOf("5s"))
                .threads(10)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    public void testStringFormat() {
        System.out.println(String.format("%d %s %f", 11, "Eleven", 1.1));
    }

    @Benchmark
    public void testStringReplace() {
        System.out.println(
                "{1} {2} {3}"
                        .replace("{1}", String.valueOf(11))
                        .replace("{2}", "Eleven")
                        .replace("{3}", String.valueOf(1.1))
        );
    }

    @Benchmark
    public void testStringReplaceFirst() {
        System.out.println(
                "{} {} {}"
                        .replaceFirst("\\{}", String.valueOf(11))
                        .replaceFirst("\\{}", "Eleven")
                        .replaceFirst("\\{}", String.valueOf(1.1))
        );
    }

    @Benchmark
    public void testStringConcat() {
        System.out.println(
                11 + " " + "Eleven" + " " + 1.1
        );
    }
}
