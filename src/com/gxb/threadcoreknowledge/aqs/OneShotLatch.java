package com.gxb.threadcoreknowledge.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 用AQS实现一个简单的线程协作器
 */
public class OneShotLatch {

    private final Sync sync = new Sync();

    public void signal() {
        sync.releaseShared(0);
    }

    public void await() {
        sync.acquireShared(0);
    }

    private static class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected int tryAcquireShared(int arg) {
            return getState() == 1 ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }
}
