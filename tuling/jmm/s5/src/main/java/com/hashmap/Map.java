package com.hashmap;

public interface Map<K, V> {
    V put(K k, V v);
    V get(K k);
    int size();
}
