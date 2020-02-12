package com.gxb.concurrenttool.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 十个线程打印日期
 */
public class ThreadLocalNormalUsage01 {

    public String date(int seconds) {
        //参数的单位是毫秒
        Date date = new Date(1000 * seconds);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 30; i++) {
            final int finalI = i;
            new Thread(() -> {
                System.out.println(new ThreadLocalNormalUsage01().date(finalI));
            }).start();
            Thread.sleep(100);
        }
    }
}
