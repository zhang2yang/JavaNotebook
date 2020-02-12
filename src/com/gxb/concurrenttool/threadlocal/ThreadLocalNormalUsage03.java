package com.gxb.concurrenttool.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1000个打印日期的任务，用线程池来执行
 */
public class ThreadLocalNormalUsage03 {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);
    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Lock lock = new ReentrantLock();

    public String date(int seconds) {
        //参数的单位是毫秒
        Date date = new Date(1000 * seconds);
        String result = null;
        // synchronized (ThreadLocalNormalUsage03.class) {
        //     result = format.format(date);
        // }
        try {
            lock.lock();
            result = format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 1000; i++) {
            final int finalI = i;
            threadPool.submit(() -> {
                new Thread(() -> {
                    System.out.println(new ThreadLocalNormalUsage03().date(finalI));
                }).start();
            });
        }
    }
}
