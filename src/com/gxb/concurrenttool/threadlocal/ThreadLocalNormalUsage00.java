package com.gxb.concurrenttool.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 两个线程打印日期
 */
public class ThreadLocalNormalUsage00 {

    public String date(int seconds) {
        //参数的单位是毫秒
        Date date = new Date(1000 * seconds);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static void main(String[] args) {
        new Thread(() -> System.out.println(new ThreadLocalNormalUsage00().date(10))).start();
        new Thread(() -> System.out.println(new ThreadLocalNormalUsage00().date(1000 ))).start();

    }
}
