package com.codecool.HashMap;

import com.codecool.LinkedLists.SinglyLinkedList.SinglyLinkedList;

import java.util.NoSuchElementException;

public class HashMap<K, V> {
    private int size = 2;
    private SinglyLinkedList<KeyValue<K, V>>[] elements;
    private int amountOfElements;

    public HashMap() {
        this.elements = new SinglyLinkedList[size];
        this.amountOfElements = 0;
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

        amountOfElements++;
        resizeIfNeeded();

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

    public void remove(K key) {
        int hash = getHash(key);
        SinglyLinkedList<KeyValue<K, V>> listOfPotentialValues = elements[hash];
        KeyValue<K, V> nodeToDelete = null;

        for (int i = 0; i < listOfPotentialValues.getLength(); i++) {
            KeyValue<K, V> node = listOfPotentialValues.get(i);
            if (node.getKey().equals(key)) {
                nodeToDelete = node;
            }
        }
        listOfPotentialValues.remove(nodeToDelete);

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

    private void resizeIfNeeded() {
        int newSize;
        if (amountOfElements > size * 2) {
            newSize = 2 * size;
            recreateElements(newSize);
        } else if (amountOfElements < size / 2) {
            newSize = size / 2;
            recreateElements(newSize);
        }
    }

    private void recreateElements(int newSize) {
        SinglyLinkedList<KeyValue<K, V>>[] oldElements = elements;
        SinglyLinkedList<KeyValue<K, V>>[] newElements = new SinglyLinkedList[newSize];
        size = newSize;
        elements = newElements;

        for (SinglyLinkedList<KeyValue<K, V>> elementsList : oldElements) {
            if (elementsList != null) {
                for (int i = 0; i < elementsList.getLength(); i++) {
                    add(elementsList.get(i).getKey(), elementsList.get(i).getValue());
                }
            }
        }
    }
}
