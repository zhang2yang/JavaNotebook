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
 *
 *
 * 区别：synchronized和Lock锁有什么区别？用新的Lock有什么好处？
 * 1.原始构成
 * synchronized是关键字属于JVM层面，
 *   monitorenter(底层是通过monitor对象来完成的，其实wait/notify等方法也是依赖于monitor对象只有在同步代码块或者方法中才能调用wait/notify方法)
 *   monitorexit
 * Lock是具体类是api层面的锁
 *
 * 2.使用方法
 * synchronized 不需要用户去手动的释放锁，当synchronized代码执行完之后会自动让线程释放对锁的占用
 * ReentrantLock 则则需要永辉手动去释放锁，就有可能导致出现死锁现象
 * 需要lock()和unlock()方法配合try/finally语句块来完成
 *
 * 3.等待是不能中断的
 * synchronized 是不可中断的，除非抛出异常或者正常运行完成
 * ReentrantLock 是可中断的 1.设置超时方法trylock(long timeout,TimeUnit unit)
 *                       2.或者设置lock.lockInterruptibly()可设置中断
 *
 *4.加锁是否公平
 * synchronized非公平锁
 * ReentrantLock两者都可
 *
 * 5.锁绑定多个条件condition
 * synchronized 没有
 * ReentrantLock 用来实现分组唤醒需要唤醒的线程们，可以精确唤醒，而不是像synchronized要么随机唤醒一个线程或者全部唤醒
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
