package com;

public class HashMap<K, V> implements Map<K, V> {
    private com.Entry<K, V>[] table = null;
    private int size = 0;

    public HashMap() {
        table = new com.Entry[16];
    }

    @Override
    public V put(K k, V v) {
        int index = hash(k);
        com.Entry<K, V> entry = table[index];

        if (null == entry) {
            table[index] = new com.Entry<>(k, v, index, null);
        } else {
            table[index] = new com.Entry<>(k, v, index, entry);
        }

        return table[index].getValue();
    }

    @Override
    public V get(K k) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    private int hash(K k) {
        int i = k.hashCode() % 16;
        return i >= 0 ? i : -i;
    }
}
