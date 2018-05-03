package com.blogspot.vitrocket.multithreading.example01;

/**
 * Created by VitRocket on 03.05.2018.
 */
public class App01 {

    public static void main(String[] args) {

        // 1 variant
        MyThread myThread1 = new MyThread(1);
        myThread1.start();

        MyThread myThread2 = new MyThread(2);
        myThread2.start();

        // 2 variant
        Thread myRunner1 = new Thread(new MyRunner(3));
        myRunner1.start();

        Thread myRunner2 = new Thread(new MyRunner(4));
        myRunner2.start();

        // 3 variant Anonymous Inner class
        @SuppressWarnings("Convert2Lambda")
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello inner!");
            }
        });
        thread1.start();

        // 3 variant Anonymous Inner class lambda
        Thread thread2 = new Thread(() -> System.out.println("Hello inner lambda!"));
        thread2.start();
    }
}

class MyThread extends Thread {

    private int id;

    MyThread(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Hello from MyThread: " + id);
    }
}

class MyRunner implements Runnable {

    private int id;

    MyRunner(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Hello from MyRunner: " + id);
    }
}