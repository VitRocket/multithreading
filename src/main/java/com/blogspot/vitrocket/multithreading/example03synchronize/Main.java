package com.blogspot.vitrocket.multithreading.example03synchronize;

/**
 * Increment in synchronized block
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        worker.doWork();
    }
}

class Worker {

    private int counter;
    private int counterBad;

    void doWork() throws InterruptedException {

        Thread thread1 = getThread();
        Thread thread2 = getThread();

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Increment in synchronized block: " + counter);
        System.out.println("Increment in no synchronized block: " + counterBad);
    }

    private Thread getThread() {
        return new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
                incrementBad();
            }
        });
    }

    private void increment() {
        synchronized (this) {
            counter++;
        }
    }

    private void incrementBad() {
        counterBad++;
    }
}
