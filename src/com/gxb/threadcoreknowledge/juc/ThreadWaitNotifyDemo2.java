package com.gxb.threadcoreknowledge.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Resource {//资源类

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signalAll();
            // condition.signal();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signalAll();
            // condition.signal();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

/**
 * 使用lock ，condition的方式实现
 *面试题:现在两个线程，可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 实现交替，来10轮，变量初始值为零
 *
 * 1.低耦合高内聚，线程操作资源类
 * 2.判断/干活/通知
 * 3.多线程交互中，必须要防止多线程的虚假唤醒，也即（判断使用while不能用if）
 */
public class ThreadWaitNotifyDemo2 {

    public static void main(String[] args) {

        Resource resource = new Resource();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resource.increment();
            }
        },"AAA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resource.decrement();
            }
        },"BBB").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resource.increment();
            }
        },"CCC").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resource.decrement();
            }
        },"DDD").start();
    }
}
