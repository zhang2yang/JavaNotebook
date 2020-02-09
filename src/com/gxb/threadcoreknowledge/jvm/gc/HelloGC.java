package com.gxb.threadcoreknowledge.jvm.gc;

/**
 * 如何查看一个正在运行中的java程序，它的某个jvm参数是否开启？具体值是多少？
 *
 * jps -l
 * jinfo
 *
 * 第一种，查看参数盘点家底
 * jps -l
 * jinfo -flag 具体家底 java进程编号
 * jinfo -flags java进程编号
 *
 * 第二种，查看参数盘点家底
 * java -XX:+PrintFlagsInitial
 */
public class HelloGC {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("HelloGC ");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
