package com.gxb.threadcoreknowledge.jvm.oom;

import sun.misc.VM;

import java.nio.ByteBuffer;

/**
 * -Xms5m -Xmx5m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 */
public class DirectBufferMemoryDemo {

    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory:" + (VM.maxDirectMemory()) / (double)1024 / 1024 + "MB");
        // -XX:MaxDirectMemorySize=5m设置的最大直接内存为5m
        ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
