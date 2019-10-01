package com.blogspot.vitrocket.multithreading.task01;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * В классе есть три метода: 1, 2, 3
 * В независимости в какой последовательности будут вызваны методы,
 * выполнить их нужно в определённой последовательности
 */
public class Task01 {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Start");

		Example01 example01 = new Example01();

		System.out.println("++++++++++++++++++++++++++++++++++");

		Thread thread1 = new Thread(example01::method2);
		Thread thread2 = new Thread(example01::method3);
		Thread thread3 = new Thread(example01::method1);

		thread1.start();
		thread2.start();
		thread3.start();

		thread1.join();
		thread2.join();
		thread3.join();

		System.out.println("++++++++++++++++++++++++++++++++++");

		thread1 = new Thread(example01::method2);
		thread2 = new Thread(example01::method3);
		thread3 = new Thread(example01::method1);

		thread1.start();
		thread2.start();
		thread3.start();

		thread1.join();
		thread2.join();
		thread3.join();

		System.out.println("++++++++++++++++++++++++++++++++++");

		System.out.println("The end");
	}
}


class Example01 {

	private AtomicBoolean done1 = new AtomicBoolean(false);
	private AtomicBoolean done2 = new AtomicBoolean(false);
	private AtomicBoolean done3 = new AtomicBoolean(false);

	Example01() {

	}

	void method1() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("method1");
		done1.set(true);
		done3.set(false);
	}

	void method2() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		while (!done1.get()) {
			try {
				System.out.println("wait done1");
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("method2");
		done2.set(true);
		done1.set(false);
	}

	void method3() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (!done2.get()) {
			try {
				System.out.println("wait done2");
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("method3");
		done3.set(true);
		done2.set(false);
	}
}
