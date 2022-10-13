package ru.vsu.cs.dolzhenkoms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {
    private final HashMap<String, Integer> testMap = new HashMap<>();

    @Test
    void isEmptyAfterInitialize() {
        assertTrue(testMap.isEmpty());
    }

    @Test
    void putNormalValue() {
        testMap.clear();
        testMap.put("1", 12);
        assertEquals(12, testMap.get("1"));
    }

    @Test
    void tryPutExistingKey() {
        testMap.put("1", 12);
        testMap.put("1", 14);
        assertTrue(testMap.containsValue(14));
    }

    @Test
    void putNotNormalValue() {
        testMap.clear();
        testMap.put("1", 1239140294);
        assertEquals(1239140294, testMap.get("1"));
    }

    @Test
    void clear() {
        testMap.clear();
        assertTrue(testMap.isEmpty());
    }

    @Test
    void sizeAfterClear() {
        testMap.size();
        assertEquals(0, testMap.size());
    }
}