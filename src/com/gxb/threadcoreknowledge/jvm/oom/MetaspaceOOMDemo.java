package com.gxb.threadcoreknowledge.jvm.oom;

/**
 *
 */
public class MetaspaceOOMDemo {

    static class OOMTest {

    }

    public static void main(String[] args) {
        //模拟计数多少次以后发生异常
        int i = 0;

        try {
            while (true) {
                i++;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println("**********多少次之后发生了异常:i=" + i);
        }
    }
}
