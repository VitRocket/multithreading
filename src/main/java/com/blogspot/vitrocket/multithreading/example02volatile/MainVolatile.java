package com.blogspot.vitrocket.multithreading.example02volatile;

/**
 * Volatile example
 * volatile — обеспечение согласованного доступа к переменной разными потоками.
 * volatile указывается, чтобы указать компилятору, что все операции присвоения этой переменной и все операции чтения из неё должны быть атомарными.
 * Присвоение значения этой переменной имеет связь happens-before (произошло-до) для последующих чтений из этой переменной для любых потоков.
 * volatile гарантирует, что все потоки всегда будут использовать общее, исходное значение, и они будут видеть изменения этого исходного значения другими потоками сразу же.
 */
public class MainVolatile {

    public static void main(String[] args) {

        MyWorker myWorker = new MyWorker();

        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(myWorker);
            thread.start();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myWorker.shutdown();
        System.out.println(myWorker.getCount());
    }
}

class MyWorker implements Runnable {

    //Если убрать volatile программа может не завершиться
    private volatile boolean running = true;
    private volatile int count = 0;

    int getCount() {
        return count;
    }

    public void run() {
        count++;
        while (running) {
            if (!running) {
                System.out.println(running);
            }
        }
    }

    void shutdown() {
        running = false;
        System.out.println("Set: " + running);
    }
}
