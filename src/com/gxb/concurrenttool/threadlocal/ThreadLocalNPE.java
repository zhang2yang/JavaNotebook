package com.gxb.concurrenttool.threadlocal;

public class ThreadLocalNPE {

    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public void set() {
        threadLocal.set(Thread.currentThread().getId());
    }

    /**
     * 此处要区分long和Long，返回long的话会抛出空指针异常
     * @return
     */
    public long get() {
        return threadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();
        System.out.println(threadLocalNPE.get());
    }
}
