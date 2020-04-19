package com.gxb.concurrenttool.threadpool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledTasksThreadPool {

    private static final ScheduledThreadPoolExecutor EXECUTOR = new ScheduledThreadPoolExecutor(1, Executors.defaultThreadFactory());

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        EXECUTOR.scheduleWithFixedDelay(() -> {
            if (hasMsgAtCurrentTime()) {
                System.out.println(df.format(new Date()));
                System.out.println("大家注意了，我要发消息了");
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    private static boolean hasMsgAtCurrentTime() {
        return true;
    }
}
