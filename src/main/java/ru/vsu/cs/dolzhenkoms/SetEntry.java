package ru.vsu.cs.dolzhenkoms;

import java.util.Objects;

public class SetEntry<V> {
    private final V value;

    private SetEntry<V> next;

    public SetEntry(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public SetEntry<V> getNext() {
        return next;
    }

    public void setNext(SetEntry<V> next) {
        this.next = next;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
