package com.codecool.HashMap;

import com.codecool.LinkedLists.SinglyLinkedList.SinglyLinkedList;

import java.util.NoSuchElementException;

public class HashMap<K, V> {
    private int size = 16;
    private SinglyLinkedList<KeyValue<K, V>>[] elements;

    public HashMap() {
        this.elements = new SinglyLinkedList[size];
    }

    public void add(K key, V value) throws IllegalStateException {
        int index = getHash(key);
        SinglyLinkedList<KeyValue<K, V>> keyValues = elements[index];

        if (keyValues == null) {
            keyValues = new SinglyLinkedList<>();
        }

        checkIfKeyExists(keyValues, key);

        keyValues.add(new KeyValue<>(key, value));
        elements[index] = keyValues;

    }

    public V getValue(K key) {
        int indexOfList = getHash(key);

        SinglyLinkedList<KeyValue<K, V>> keyValues = elements[indexOfList];

        if (keyValues != null) {

            for (int i = 0; i < keyValues.getLength(); i++) {
                if (keyValues.get(i).getKey().equals(key)) {
                    return keyValues.get(i).getValue();
                }
            }
        }
        throw new NoSuchElementException("Can't find value assigned to the key!");
    }


    private void checkIfKeyExists(SinglyLinkedList<KeyValue<K, V>> keyValues, K key) {
        if (keyValues.getLength() > 0) {
            for (int i = 0; i < keyValues.getLength(); i++) {
                if (keyValues.get(i).getKey().equals(key)) {
                    throw new IllegalStateException("Key already exists, key must be unique!");
                }
            }
        }
    }

    private int getHash(K key) {
        int hash = Math.abs(key.hashCode() % size);
        return hash;
    }
}
