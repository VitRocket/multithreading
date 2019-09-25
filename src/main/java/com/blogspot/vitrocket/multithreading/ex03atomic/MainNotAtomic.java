package com.blogspot.vitrocket.multithreading.ex03atomic;

public class MainNotAtomic {

    public static void main(String[] args) throws InterruptedException {

        ProcessingThreadBad pt = new ProcessingThreadBad();
        Thread t1 = new Thread(pt, "t1");
        t1.start();
        Thread t2 = new Thread(pt, "t2");
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Processing count=" + pt.getCount());
    }
}

class ProcessingThreadBad implements Runnable {
    private int count;

    @Override
    public void run() {
        for (int i = 1; i < 10; i++) {
            processSomething();
            count++;
        }
    }

    int getCount() {
        return this.count;
    }

    private void processSomething() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
