package ru.vsu.cs.dolzhenkoms;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class Bucket<K,V> {
    private final K key;
    private V value;
    private Bucket<K,V> next;

    public Bucket(K key, @Nullable V value) {
        this.key = key;
        this.value = value;
    }

    public Bucket(K key) {
        this.key = key;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public Bucket<K, V> getNext() {
        return next;
    }

    public void setValue(@NotNull V value) {
        this.value = value;
    }

    public void setNext(@NotNull Bucket<K, V> next) {
        this.next = next;
    }

    @Override
    public int hashCode() {
        return Math.abs(Objects.hashCode(key));
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
