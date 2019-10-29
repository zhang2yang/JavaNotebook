package com.gxb.threadcoreknowledge.juc.pool;


import java.util.concurrent.*;

public class MyThreadPoolDemo {

    public static void main(String[] args) {
        // initPool();

        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                5,
                2l,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void initPool() {
        //一池5个工作线程，类似一个银行有5个受理串口
        // ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //一池1个工作线程，类似一个银行有1个受理串口
        // ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //一池1个工作线程，类似一个银行有1个受理串口
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            //模拟有10个顾客过来参加银行办理业务，目前池子里面有5个工作人员提供服务
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
