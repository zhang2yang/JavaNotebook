package com.gxb.threadcoreknowledge.juc.pattern;

public class SingletonDemo {

    public static void main(String[] args) {
        for (int i = 1; i <= 10 ; i++) {
            new Thread(() -> {
                SingletonDCL singleton = SingletonDCL.getSingleton();
                System.out.println(Thread.currentThread().getName() + "\t" + singleton);
            },String.valueOf(i)).start();
        }
    }
}

class SingletonDCL {

    private SingletonDCL() {
        System.out.println("\t单例模式的对象已经被初始化");
    }

    private static volatile SingletonDCL singleton = null;

    public static SingletonDCL getSingleton() {
        if (singleton == null) {
            synchronized (SingletonDCL.class) {
                if (singleton == null) {
                    singleton = new SingletonDCL();
                }
            }
        }
        return singleton;
    }

}
