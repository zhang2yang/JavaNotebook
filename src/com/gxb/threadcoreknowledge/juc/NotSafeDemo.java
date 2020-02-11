package com.gxb.threadcoreknowledge.juc;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 解决方案
 *  1.Vector
 *  2.Collections.synchronizedList();
 *  3.CopyOnWriteArrayList()
 */
public class NotSafeDemo {

    public static void main(String[] args) {

        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }

    }
}
