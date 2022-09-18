package com.pkj.parallelProgramming;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class main {
    public static void main(String[] args) throws Exception {
        List<Integer> numbers = Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

        process(numbers.parallelStream()
                .map(e -> transform(e)));

    }

    private static void process(Stream<Integer> stream) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool(100);

        pool.submit(() -> stream.forEach(e -> {}));
        pool.shutdownNow();
        pool.awaitTermination(10, TimeUnit.SECONDS);
    }

    private static void use(List<Integer> numbers) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(ForkJoinPool.commonPool());
        numbers.stream()
                .parallel()
                .map(e -> transform(e))
                .forEach(System.out::println);
    }

    private static int transform(int n) {
        System.out.println("t:" + n + "--" + Thread.currentThread());
        sleep(1000);
        return n * 1;
    }

    private static boolean sleep(int ms) {
        try {
            Thread.sleep(ms);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }
}
