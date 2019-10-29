package com.gxb.threadcoreknowledge.juc;

class ShareResource {//资源类

    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        //判断
        while (number != 0) {
            wait();
        }
        //干活
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number );
        //通知
        // notify();
        notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (number == 0) {
            wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        // notify();
        notifyAll();
    }
}

/**
 * wait()、notify()、notifyAll()写法
 *面试题:现在两个线程，可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 实现交替，来10轮，变量初始值为零
 *
 * 1.低耦合高内聚，线程操作资源类
 * 2.判断/干活/通知
 * 3.多线程交互中，必须要防止多线程的虚假唤醒，也即（判断使用while不能用if）
 */
public class ThreadWaitNotifyDemo {

    public static void main(String[] args) {

        ShareResource shareResource = new ShareResource();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}
