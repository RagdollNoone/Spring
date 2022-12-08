package com;

public class Entry<K, V> implements Map.Entry<K, V> {
    private K k;
    private V v;
    private int hash;
    Entry<K, V> next;

    public Entry(K k, V v, int hash, Entry<K, V> next) {
        this.k = k;
        this.v = v;
        this.hash = hash;
        this.next = next;
    }

    @Override
    public K getKey() {
        return k;
    }

    @Override
    public V getValue() {
        return v;
    }
}
