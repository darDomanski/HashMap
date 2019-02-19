package com.codecool.HashMap;

public class KeyValue<K, V> {
    private K key;
    private V value;

    public KeyValue(K key, V value) {
        if (key == null || value == null) throw new IllegalStateException("Null can't be a key nor value!");
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
