package com.gxb.collection;

public class HashMap<K,V> implements Map<K,V> {

    private Node<K,V>[] table;
    private int size;
    private static int defaultLength = 16;

    public HashMap() {
        table = new Node[defaultLength];
    }

    @Override
    public V put(K k, V v) {
        //通过hash把key算出来
        int index = hash(k);
        Node<K, V> node = table[index];
        if (node == null) {
            table[index] = new Node<>(k, v, index, null);
            this.size++;
        }else {
            table[index] = new Node<>(k, v, index, node);
        }
        return table[index].getValue();
    }

    private int hash(K k) {
        int index = k.hashCode() % (defaultLength - 1);
        return Math.abs(index);
    }

    @Override
    public V get(K k) {
        if (size == 0 || k == null) {
            return null;
        }
        int index = hash(k);
        Node<K, V> entry = getEntry(k, index);
        return entry == null ? null : entry.getValue();
    }

    private Node<K,V> getEntry(K k, int index) {
        for (Node<K,V> node = table[index]; node != null; node = node.next) {
            if ((node.key == k || k.equals(node.getKey())) && node.hash == index) {
                return node;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        HashMap<Object,Object> hashMap = new HashMap<>();
        hashMap.put("name", "gxb");
        hashMap.put("age", 22);
        System.out.println(hashMap.get("name"));
        System.out.println(hashMap.get("age"));
    }

    @Override
    public int size() {
        return this.size;
    }

    private static class Node<K,V> implements Map.Entry {

        K key;
        V value;
        int hash;
        Node<K,V> next;

        private Node() {}

        private Node(K key, V value, int hash, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }
}
