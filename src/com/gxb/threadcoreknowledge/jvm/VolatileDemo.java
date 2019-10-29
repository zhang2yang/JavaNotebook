package com.gxb.threadcoreknowledge.jvm;

class Resource {
    volatile int number = 25;

    public void setNumber() {
        this.number = 100;
    }
}

public class VolatileDemo {

    public static void main(String[] args) {

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
