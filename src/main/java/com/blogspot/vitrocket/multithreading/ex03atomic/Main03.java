package com.blogspot.vitrocket.multithreading.ex03atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Main03 {

    public static void main(String[] args) throws InterruptedException {
        incrementSolution();
    }

    private static void incrementSolution() throws InterruptedException {
        AtomicInteger atomicInt = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
                .forEach(i -> executor.submit(atomicInt::incrementAndGet));

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);

        System.out.println(atomicInt.get());    // => 1000

    }
}
