package com.gxb.concurrenttool.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 利用ThreadLocal，给每个线程分配自己的dateFormat对象，保证了线程安全，高效利用内存
 */
public class ThreadLocalNormalUsage05 {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public String date(int seconds) {
        //参数的单位是毫秒
        Date date = new Date(1000 * seconds);
        // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = ThreadSoftFormatter.dateFormatThreadLocal.get();
        return format.format(date);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 1000; i++) {
            final int finalI = i;
            threadPool.submit(() -> {
                new Thread(() -> {
                    System.out.println(new ThreadLocalNormalUsage05().date(finalI));
                }).start();
            });
        }
        threadPool.shutdown();
    }
}

class ThreadSoftFormatter {

    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
}
