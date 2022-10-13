package ru.vsu.cs.dolzhenkoms;


import java.util.List;

public class HashMultiMap<K, V> {
    private HashMap<K, HashSet<V>> maps;

    public HashMultiMap(int capacity) {
        maps = new HashMap<>(capacity);
    }

    public HashMultiMap() {
        this(16);
    }

    public HashSet<V> get(K key) {
        return (HashSet<V>) maps.get(key);
    }

    public void put(K key, V value) {
        if (maps.containsKey(key)) {
            var set = get(key);
            if (set.containsValue(value)) {
                return;
            }
            set.put(value);
        } else {
            maps.put(key, new HashSet<>());
            get(key).put(value);
        }
    }

    public V get(K key, V value) {
        if(!containsKey(key)) {
            return null;
        }

        return get(key).get(value);
    }

    public void remove(K key, V value) {
        if(!containsKey(key)) {
            return;
        }
        ((HashSet<V>) maps.get(key)).remove(value);
    }

    public boolean containsKey(K key) {
        return maps.containsKey(key);
    }

    public int size() {
        int count = 0;

        for(var key : maps.keys()) {
            count += ((HashSet<V>) maps.get(key)).size();
        }

        return count;
    }

    public boolean isEmpty() {
        for(var key : maps.keys()) {
            if(((HashSet<V>) maps.get(key)).size() > 0)
                return false;
        }
        return true;
    }

    public List<K> keys() {
        return maps.keys();
    }

    public void clear() {
        maps = new HashMap<K, HashSet<V>>();
    }
}
