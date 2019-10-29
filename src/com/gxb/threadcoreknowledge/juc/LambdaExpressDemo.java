package com.gxb.threadcoreknowledge.juc;

@FunctionalInterface
interface Foo {
    void sayHello();

    default int add(int x, int y) {
        return x + y;
    }

    static int mul(int x, int y) {
        return x * y;
    }
}

public class LambdaExpressDemo {

    public static void main(String[] args) {
        Foo foo = () -> System.out.println("Lambada Express");
        foo.sayHello();
    }
}
