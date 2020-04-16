package com.gxb.threadcoreknowledge.jvm;

public class T2 {

    public static void main(String[] args) {
        // long maxMemory = Runtime.getRuntime().maxMemory();
        // long totalMemory = Runtime.getRuntime().totalMemory();
        // System.out.println("-Xmx:MAX_MEMORY: = " + (double)maxMemory / 1024 / 1024 + "MB");
        // System.out.println("-Xms:TOTAL_MEMORY: = " + (double)totalMemory / 1024 / 1024 + "MB");
        // byte[] bytes = new byte[40 * 1024 * 1024];
        Integer a = 10000;
        Integer b = 10000;
        System.out.println(a==b);
    }
}
