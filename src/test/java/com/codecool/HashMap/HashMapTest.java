package com.codecool.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HashMapTest {
    private HashMap<String, String> map;

    @BeforeEach
    private void initializeMap() {
        this.map = new HashMap<>();
    }

    @Test
    public void testAddOneElement() {
        map.add("dog", "Max");
        String value = map.getValue("dog");

        String expected = "Max";

        assertEquals(expected, value);
    }

    @Test
    public void testFewElements() {
        map.add("dog", "Pluto");
        map.add("duck", "Donald");
        map.add("bird", "Tweety");

        String value = map.getValue("dog");
        String expected = "Pluto";

        assertEquals(value, expected);

        value = map.getValue("duck");
        expected = "Donald";

        assertEquals(value, expected);

        value = map.getValue("bird");
        expected = "Tweety";

        assertEquals(value, expected);

    }

    @Test
    public void testAddElementWithSameKey() {
        map.add("dog", "Pluto");

        assertThrows(IllegalStateException.class, () -> map.add("dog", "Pluto"));
    }

    @Test
    public void testGetNotExistingElement() {
        map.add("dog", "Pluto");

        assertThrows(NoSuchElementException.class, () -> map.getValue("bird"));
    }

    @Test
    public void testRemoveElement() {
        map.add("dog", "Pluto");
        map.add("duck", "Donald");
        map.add("bird", "Tweety");

        map.remove("bird");

        assertThrows(NoSuchElementException.class, () -> map.getValue("bird"));
    }

}