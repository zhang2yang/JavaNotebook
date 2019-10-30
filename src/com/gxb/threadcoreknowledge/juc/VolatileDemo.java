package com.gxb.threadcoreknowledge.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**1.验证volatile的可见性
 * 1.1 volatile可以保证可见性，及时通知其他线程，主物理内存的值已经被修改了
 * 2.2 添加了volatile，可以解决可见性问题
 *
 * 2.验证volatile不保证原子性
 *2.1 原子性指的是什么意思
 *  不可分割，完整性，也即某个线程正在做某个具体业务中，中间不可以被加塞或者被分割，需要整体完整
 *  要么同时成功，要么同时失败
 *
 *2.3 why
 *
 * 2.4 如何解决原子性
 *  加sync
 *  使用juc下的AtomicInteger
 */
public class VolatileDemo {

    static class Resource {

        private volatile int number = 0;

        private AtomicInteger atomicInteger = new AtomicInteger(0);

        private void setNumber() {
            this.number = 100;
        }

        private void addAtomicInteger() {
            atomicInteger.getAndIncrement();
        }

        private void addPlusPlus() {
            this.number++;
        }
    }

    public static void main(String[] args) {
        //程序执行默认存在的线程有2个
        // System.out.println(Thread.activeCount());
        Resource resource = new Resource();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000 ; j++) {
                    resource.addPlusPlus();
                    resource.addAtomicInteger();
                }
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t int type,final number value:" + resource.number);
        System.out.println(Thread.currentThread().getName() + "\t AtomicInteger type,final number value:" + resource.atomicInteger.get());
    }

    private static void seeOkByValatile() {
        Resource resource = new Resource();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "开始执行...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.setNumber();
            System.out.println(Thread.currentThread().getName() + "已经改变了number值,value:" + resource.number);
        },"A").start();

        while (resource.number == 25) {

        }
        System.out.println(Thread.currentThread().getName() + "执行完毕");
    }


}
