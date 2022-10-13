package ru.vsu.cs.dolzhenkoms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashMultiMapTest {
    private final HashMultiMap<String, Integer> testMap = new HashMultiMap<>();

    @Test
    void isEmptyAfterInitialize() {
        assertTrue(testMap.isEmpty());
    }

    @Test
    void putNormalValue() {
        testMap.clear();
        testMap.put("1", 12);
        assertTrue(testMap.get("1").containsValue(12));
    }

    @Test
    void tryPutExistingKey() {
        int size = testMap.size();
        testMap.put("1", 12);
        testMap.put("1", 14);
        int newSize = testMap.size();
        assertEquals(2, newSize - size);
    }

    @Test
    void putNotNormalValue() {
        testMap.clear();
        testMap.put("1", 1239140294);
        assertTrue(testMap.get("1").containsValue(1239140294));
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