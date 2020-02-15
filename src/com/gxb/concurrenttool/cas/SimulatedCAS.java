package com.gxb.concurrenttool.cas;

public class SimulatedCAS {

    private volatile int value;

    public synchronized int compareAndSawp(int expectedValue,int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }
}
