package ru.vsu.cs.dolzhenkoms;

import java.util.Arrays;
import java.util.Objects;

public class HashSet<V> {
    private SetEntry<V>[] entries;

    private int capacity;

    public HashSet(int capacity) {
        this.capacity = capacity;
        this.entries = new SetEntry[capacity];
    }

    public HashSet() {
        this(16);
    }

    public void put(V value) {
        if (containsValue(value)) {
            return;
        }
        SetEntry<V> newEntry = new SetEntry<>(value);
        int hashNewEntry = hash(newEntry);

        var temp = entries[hashNewEntry];

        if(temp == null) {
            entries[hashNewEntry] = newEntry;
            return;
        }

        while (temp.getNext() != null) {
            temp = temp.getNext();
        }

        temp.setNext(newEntry);
    }

    public V get(V value) {
        if (!containsValue(value)) {
            return null;
        }
        int hashEntry = hash(new SetEntry<>(value));

        if (entries[hashEntry].getValue().equals(value)) {
            return entries[hashEntry].getValue();
        }

        var temp = entries[hashEntry];

        while (temp.getNext() != null) {
            if (temp.getValue().equals(value)) {
                return temp.getValue();
            }
            temp = temp.getNext();
        }

        if(temp.getValue().equals(value)) {
            return temp.getValue();
        }

        return null;
    }

    public void remove(V value) {
        if (!containsValue(value)) {
            return;
        }

        int hashEntry = hash(new SetEntry<>(value));

        if (entries[hashEntry].getValue().equals(value)) {
            entries[hashEntry] = entries[hashEntry].getNext();
            return;
        }
        var previous = entries[hashEntry];
        while (previous != null) {
            if (previous.getNext().getValue().equals(value)) {
                previous.setNext(previous.getNext().getNext());
                return;
            }
            previous.setNext(previous.getNext());
        }
    }

    public boolean containsValue(V value) {
        SetEntry<V> newEntry = new SetEntry<>(value);
        int hashNewEntry = hash(newEntry);

        if (entries[hashNewEntry] == null) {
            return false;
        }

        if (entries[hashNewEntry].getValue().equals(newEntry.getValue())) {
            return true;
        }

        var temp = entries[hashNewEntry].getNext();

        while (temp != null) {
            if (temp.getValue().equals(newEntry.getValue())) {
                return true;
            }
            temp = temp.getNext();
        }

        return false;
    }

    public int size() {
        int count = 0;

        for (SetEntry<V> bucket : Arrays.stream(entries).filter(Objects::nonNull).toList()) {
            var temp = bucket;
            while (temp != null) {
                count++;
                temp = temp.getNext();
            }
        }

        return count;
    }

    private int hash(SetEntry<V> entry) {
        return entry.hashCode() % capacity;
    }
}
