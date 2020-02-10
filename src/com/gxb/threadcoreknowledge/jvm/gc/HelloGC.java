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
 *
 *
 * -XX“:+UseSerialGC     串行垃圾回收器
 * -XX:+UseParallelGC   并行垃圾回收器
 *
 * =================================
 * 查看JVM初始参数
 * -XX:InitialHeapSize=266044224  254M
 * -XX:MaxHeapSize=4256707584   4G
 * -XX:+PrintCommandLineFlags
 * -XX:+UseCompressedClassPointers
 * -XX:+UseCompressedOops
 * -XX:-UseLargePagesIndividualAllocation
 * -XX:+UseParallelGC
 * =================================
 *
 *
 * 修改JVM参数
 * -Xms128m -Xmx4096m -Xss1024k -XX:MetaspaceSize=512m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseSerialGC
 *
 * -XX:InitialHeapSize=134217728
 * -XX:MaxHeapSize=4294967296
 * -XX:MetaspaceSize=536870912
 * -XX:+PrintCommandLineFlags
 * -XX:+PrintGCDetails
 * -XX:ThreadStackSize=1024
 * -XX:+UseCompressedClassPointers
 * -XX:+UseCompressedOops
 * -XX:-UseLargePagesIndividualAllocation
 * -XX:+UseSerialGC
 */
public class HelloGC {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("HelloGC ");
        // Thread.sleep(Integer.MAX_VALUE);
        byte[] bytes = new byte[50 * 1024 * 1024];
    }
}
