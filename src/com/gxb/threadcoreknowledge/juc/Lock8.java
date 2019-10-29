package com.gxb.threadcoreknowledge.juc;

import java.util.concurrent.TimeUnit;

class Phone {

    public synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("send Email");
    }

    public synchronized void sendMsg() {
        System.out.println("send message");
    }

    public void hello() {
        System.out.println("send hello");
    }
}

public class Lock8 {

    public static void main(String[] args) {

        Phone phone = new Phone();
        Phone phone1 = new Phone();

        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            phone1.sendMsg();
        }).start();

    }
}
