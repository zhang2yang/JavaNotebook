package com.gxb.threadcoreknowledge.jvm.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceDemo {

    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(object, referenceQueue);

        System.out.println(object);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
        System.out.println("=====================");

        object = null;
        System.gc();
        Thread.sleep(600);

        System.out.println(object);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
    }
}
