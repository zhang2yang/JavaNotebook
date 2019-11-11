package com.gxb.threadcoreknowledge.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket {
    //资源类 = 实例变量 + 实例方法

    private int number = 30;

    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                Thread.sleep(10);
                System.out.println(Thread.currentThread().getName() + "\t卖出第:" + (number--) + "张票\t还剩下" + number);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

public class SaleTicketDemo01 {

    public static void main(String[] args) {

        Ticket ticket = new Ticket();
        new Thread(() -> { for (int i = 0; i <  40; i++) ticket.sale(); },"A售票员").start();
        new Thread(() -> { for (int i = 0; i <  40; i++) ticket.sale(); },"B售票员").start();
        new Thread(() -> { for (int i = 0; i <  40; i++) ticket.sale(); },"C售票员").start();

    }
}
