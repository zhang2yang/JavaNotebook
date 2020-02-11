package com.gxb.threadcoreknowledge.jvm.gc;

import java.util.Random;

/**
 * 1.-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC (defNew+Tenured)
 *
 * 2.-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC (ParNew+Tenured)
 * Java HotSpot(TM) 64-Bit Server VM warning: Using the ParNew young collector with the Serial old collector is deprecated and will likely be removed in a future release
 * 备注：-XX:ParallelGCThreads:限制线程数量，默认开始和cpu数量相同的线程数
 *
 * 3.-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC (PSYoungGen+ParOldGen)
 *
 * 4.-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC (PSYoungGen+ParOldGen)
 *
 * 5.-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC (ParNew+concurrent-mark-sweep)
 *
 * （Java8中已经被优化，已经没有了）
 * 6.-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialOldGC (ParNew+concurrent-mark-sweep)
 *
 *
 */
public class GCDemo {

    public static void main(String[] args) {
        System.out.println("***********GCDemo hello");
        StringBuilder str = new StringBuilder("Geligamesh");
        try {
            while (true) {
                str.append(str.toString()).append(new Random().nextInt(777777777)).append(new Random().nextInt(888888888));
                str.toString().intern();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
