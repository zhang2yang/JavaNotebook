package com.gxb.threadcoreknowledge.juc.pool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //同步、异步回调
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t completableFuture1");
        });
        System.out.println(voidCompletableFuture.get());

        //异步回调
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t completableFuture2");
            // int i = 10 / 0;
            return 1024;
        });

        System.out.println(integerCompletableFuture.whenComplete((integer, throwable) -> {
            System.out.println("integer:" + integer);
            System.out.println("throwable:" + throwable.getMessage());
        }).exceptionally(throwable -> {
            System.out.println("exception:" + throwable.getMessage());
            return 404;
        }).get());
    }
}
