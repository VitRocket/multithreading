package com.blogspot.vitrocket.multithreading.ex02deadlock.solution;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by VitRocket on 06.05.2018.
 */
public class AppSolution {

    public static void main(String[] args) throws InterruptedException {

        Runner runner = new Runner();

        Thread thread1 = new Thread(() -> runner.firstThread());
        Thread thread2 = new Thread(() -> runner.secondThread());

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        runner.finished();
    }
}

class Runner {

    private Account account1 = new Account();
    private Account account2 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    /*
    Solution deadlock
     */
    private void getLocks(Lock lock1, Lock lock2) {
        boolean gotLock1 = false;
        boolean gotLock2 = false;

        while (true) {
            try {
                gotLock1 = lock1.tryLock();
                gotLock2 = lock2.tryLock();
            } finally {
                if (gotLock1 && gotLock2) {
                    return;
                }

                if (gotLock1) {
                    lock1.unlock();
                }

                if (gotLock2) {
                    lock2.unlock();
                }
            }

            try {
                System.out.println("lock");
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void firstThread() {
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            getLocks(lock1, lock2);
            try {
                Account.transfer(account1, account2, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    void secondThread() {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            getLocks(lock2, lock1);
            try {
                Account.transfer(account2, account1, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    void finished() {
        System.out.println("account1 balance: " + account1.getBalance());
        System.out.println("account1 balance: " + account2.getBalance());
        System.out.println("Total balance " + (account1.getBalance() + account2.getBalance()));
    }

}

class Account {

    private int balance = 10000;

    private void deposit(int amount) {
        balance += amount;
    }

    private void withDraw(int amount) {
        balance -= amount;
    }

    int getBalance() {
        return balance;
    }

    static void transfer(Account account1, Account account2, int amount) {
        account1.withDraw(amount);
        account2.deposit(amount);
    }

}
