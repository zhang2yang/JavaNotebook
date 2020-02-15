package com.gxb.concurrenttool.cas;

public class TwoThreadsCompetition implements Runnable{

    private volatile int value;

    public synchronized int compareAndSawp(int expectedValue,int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }

    @Override
    public void run() {

        compareAndSawp(0,1);
    }

    public static void main(String[] args) throws InterruptedException {
        TwoThreadsCompetition twoThreadsCompetition = new TwoThreadsCompetition();
        twoThreadsCompetition.value = 0;
        Thread t1 = new Thread(twoThreadsCompetition);
        Thread t2 = new Thread(twoThreadsCompetition);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(twoThreadsCompetition.value);
    }
}
