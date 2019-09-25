package com.blogspot.vitrocket.multithreading.example04pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainFixedPool {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 10; i++) {
            executor.submit(new Work(i));
        }
        executor.shutdown();
        System.out.println("All tasks submitted");
        executor.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("Good");
    }
}

class Work implements Runnable {

    private int id;

    Work(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Work " + id + " was complete");
    }
}
