package com.blogspot.vitrocket.multithreading.ex03atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class MainAtomic {

    public static void main(String[] args) throws InterruptedException {

        ProcessingThread pt = new ProcessingThread();
        Thread t1 = new Thread(pt, "t1");
        t1.start();
        Thread t2 = new Thread(pt, "t2");
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Processing count=" + pt.getCount());
    }
}

class ProcessingThread implements Runnable {
    private AtomicInteger count = new AtomicInteger();

    @Override
    public void run() {
        for (int i = 1; i < 10; i++) {
            processSomething();
            count.incrementAndGet();
        }
    }

    int getCount() {
        return this.count.get();
    }

    private void processSomething() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
