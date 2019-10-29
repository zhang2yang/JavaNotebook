package com.gxb.threadcoreknowledge.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() -> System.out.println("集结7龙珠，召唤神龙"));

        for (int i = 1; i <= 7; i++) {
            int j = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t集结第" + j + "颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
