package com.gxb.threadcoreknowledge.jvm.ref;

import java.util.HashMap;
import java.util.WeakHashMap;

public class WeakHashMapDemo {

    public static void main(String[] args) {
        myHashMap();
        System.out.println("====================");
        myWeakHashMap();
    }

    private static void myHashMap() {
        HashMap<Integer,String> map = new HashMap<>();
        Integer key = 1;
        String value = "hashMap";
        map.put(key, value);
        System.out.println(map.toString());

        key = null;
        System.out.println(map.toString());

        System.gc();
        System.out.println(map.toString());

    }

    private static void myWeakHashMap() {
        WeakHashMap<Integer,String> map = new WeakHashMap<>();
        Integer key = 2;
        String value = "weakHashMap";
        map.put(key, value);
        System.out.println(map.toString());

        key = null;
        System.out.println(map.toString());

        System.gc();
        System.out.println(map.toString());
    }
}
