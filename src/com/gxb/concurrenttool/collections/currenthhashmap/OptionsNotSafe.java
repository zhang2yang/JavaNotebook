package com.gxb.concurrenttool.collections.currenthhashmap;

import java.util.concurrent.ConcurrentHashMap;

public class OptionsNotSafe implements Runnable{

    private static ConcurrentHashMap<String,Integer> concurrentHashMap = new ConcurrentHashMap<>();

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            // Integer score = concurrentHashMap.get("gxb");
            // Integer newScore = score + 1;
            // boolean result = concurrentHashMap.replace("gxb", score, newScore);
            Integer score;
            do{
                score = concurrentHashMap.get("gxb");
            }while (!concurrentHashMap.replace("gxb",score, score + 1));
            // concurrentHashMap.put("gxb",newScore);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        concurrentHashMap.put("gxb",0);

        OptionsNotSafe optionsNotSafe = new OptionsNotSafe();
        Thread t1 = new Thread(optionsNotSafe);
        Thread t2 = new Thread(optionsNotSafe);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(concurrentHashMap.get("gxb"));
        long end = System.currentTimeMillis();
        System.out.println("time:" + (double)(end - start) / 1000);
    }
}
