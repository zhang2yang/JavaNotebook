package com.gxb.threadcoreknowledge.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable{

    private Lock lock = new ReentrantLock();

    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName() + "\t invoked sendSMS");
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + "\t invoked sendEmail");
        sendSMS();
    }

    @Override
    public void run() {
        get();
    }

    public void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoked get");
            set();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoked set");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 可重入锁（也叫做递归锁）
 * 指的是同一个线程外层方法获取锁之后，内层递归方法仍然能获取该锁的代码
 * 当同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁
 *
 * synchronized和reentrantLock都是典型的可重入锁
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {

        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendEmail();
        },"t1").start();

        new Thread(() -> {
            phone.sendEmail();
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        new Thread(phone,"t3").start();
        new Thread(phone,"t4").start();
    }
}
