package ru.vsu.cs.dolzhenkoms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class HashMap<K, V> {
    private Bucket<K, V>[] buckets;
    private int capacity;

    public HashMap() {
        this(16);
    }

    public HashMap(int capacity) {
        this.capacity = capacity;
        buckets = new Bucket[capacity];
    }

    public void put(K key, V value) {
        Bucket<K, V> newBucket = new Bucket<>(key, value);
        int bucketHash = hash(newBucket);

        if (buckets[bucketHash] == null) {
            buckets[bucketHash] = newBucket;
        } else {
            expandBuckets();
            if (buckets[bucketHash].getKey().equals(newBucket.getKey())) {
                buckets[bucketHash].setValue(newBucket.getValue());
            } else {
                var temp = buckets[bucketHash];
                while(temp.getNext() != null) {
                    temp = temp.getNext();
                }
                temp.setNext(newBucket);
            }
        }
    }

    public Object get(K key) {
        return getBucket(key) == null ? "null" : getBucket(key).getValue();
    }

    public boolean containsKey(K key) {
        return getBucket(key) != null;
    }

    public boolean containsValue(V value) {
        for (Bucket<K, V> bucket : Arrays.stream(buckets).filter(Objects::nonNull).toList()) {
            var temp = bucket;
            do {
                if (temp.getValue().equals(value)) {
                    return true;
                }
                temp = temp.getNext();
            } while (temp != null);
        }

        return false;
    }

    public void clear() {
        buckets = new Bucket[capacity];
    }

    public List<K> keys() {
        List<K> keys = new ArrayList<>();

        for (Bucket<K, V> bucket : Arrays.stream(buckets).filter(Objects::nonNull).toList()) {
            var temp = bucket;
            while (temp != null) {
                keys.add(temp.getKey());
                temp = temp.getNext();
            }
        }

        return keys;
    }
    public int size() {
        int count = 0;

        for (Bucket<K, V> bucket : Arrays.stream(buckets).filter(Objects::nonNull).toList()) {
            var temp = bucket;
            while (temp != null) {
                count++;
                temp = temp.getNext();
            }
        }

        return count;
    }

    public boolean isEmpty() {
        return Arrays.stream(buckets).allMatch(Objects::isNull);
    }

    private int hash(Bucket<K, V> bucket) {
        return bucket.hashCode() % capacity;
    }

    private Bucket<K, V> getBucket(K key) {
        int bucketHash = hash(new Bucket<K, V>(key));

        if (buckets[bucketHash] == null) {
            return null;
        }

        var existedBucket = buckets[bucketHash];
        do {
            if (existedBucket.getKey().equals(key)) {
                return existedBucket;
            }
            existedBucket = existedBucket.getNext();
        } while (existedBucket.getNext() != null);

        return existedBucket;
    }

    private void expandBuckets() {
        if (Arrays.stream(buckets).anyMatch(Objects::isNull)) {
            return;
        }
        capacity *= 2;
        Bucket<K, V>[] expandedBuckets = new Bucket[capacity];

        System.arraycopy(buckets, 0, expandedBuckets, 0, buckets.length);
        buckets = expandedBuckets;
    }
}
