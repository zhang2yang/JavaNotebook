package com.gxb.threadcoreknowledge.jvm.ref;

import java.lang.ref.WeakReference;

public class WeakReferenceDemo {

    public static void main(String[] args) {
        Object object = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(object);
        System.out.println(object);
        System.out.println(weakReference.get());

        object = null;
        System.gc();
        System.out.println("=====================");

        System.out.println(object);
        System.out.println(weakReference.get());

    }
}
