package com.gxb.concurrenttool.collections.predecessor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 描述：     演示Map的基本用法
 */
public class MapDemo {
    public static void main(String[] args) {
        int hashCode = "gxb".hashCode();
        System.out.println(hashCode);
        int code = hashCode ^ (hashCode >>> 16);
        System.out.println(code);
    }
}

class Node {

    private int hash;
    private String key;
    private String value;

    public Node(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }
}
