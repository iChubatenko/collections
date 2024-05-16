package com.ihorchubatenko.collections.hashMap;

import com.ihorchubatenko.collections.arrayList.MyArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyHashMap<K, V> implements Iterable<Entry<K, V>> {

    private static final int DEFAULT_CAPACITY = 16;
    private MyArrayList<Entry<K, V>>[] buckets;
    private int size;

    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashMap(int capacity) {
        buckets = new MyArrayList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new MyArrayList<>();
        }
        size = 0;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new MyHashMapIterator();
    }

    private int hash(K key) {
        int hash = 0;
        for (int i = 0; i < key.toString().length(); i++) {
            hash = (31 * hash + key.toString().charAt(i));
        }
        return Math.abs(hash % buckets.length);
    }

    public void put(K key, V value) {
        int index = hash(key);
        MyArrayList<Entry<K, V>> bucket = buckets[index];
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        bucket.add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        MyArrayList<Entry<K, V>> bucket = buckets[index];
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                System.out.println(entry.getValue());
                return entry.getValue();
            }
        }
        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        MyArrayList<Entry<K, V>> bucket = buckets[index];
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                bucket.remove(index);
                size--;
                return;
            }
        }
    }

    public int size() {
        System.out.println(size);
        return size;
    }

    public void clear() {
        for (MyArrayList<Entry<K, V>> bucket : buckets) {
            bucket.removeAll();
        }
        size = 0;
    }

    public boolean containsKey(K key) {
        int index = hash(key);
        MyArrayList<Entry<K, V>> bucket = buckets[index];
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (MyArrayList<Entry<K, V>> bucket : buckets)
            for (Entry<K, V> entry : bucket) {
                if (entry.getValue().equals(value)) {
                    return true;
                }
            }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public MyArrayList<K> keyArray() {
        MyArrayList<K> keys = new MyArrayList<>();
        for (MyArrayList<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    public MyArrayList<V> valuesArray() {
        MyArrayList<V> values = new MyArrayList<>();
        for (MyArrayList<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                values.add(entry.getValue());
            }
        }
        return values;
    }

    public MyArrayList<Entry<K, V>> entryArray() {
        MyArrayList<Entry<K, V>> entries = new MyArrayList<>();
        for (MyArrayList<Entry<K, V>> bucket : buckets) {
            entries.addAll(bucket);
        }
        return entries;
    }

    private class MyHashMapIterator implements Iterator<Entry<K, V>> {
        private int currentIndex = 0;
        private Iterator<Entry<K, V>> currentIterator = null;

        @Override
        public boolean hasNext() {
            if (currentIterator != null && currentIterator.hasNext()) {
                return true;
            }
            while (currentIndex < buckets.length) {
                if (buckets[currentIndex].isEmpty()) {
                    currentIndex++;
                } else {
                    currentIterator = buckets[currentIndex].iterator();
                    return true;
                }
            }
            return false;
        }

        @Override
        public Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return currentIterator.next();
        }
    }
}
