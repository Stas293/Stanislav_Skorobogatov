package org.projects.development;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Task1Test {
    private Task1 task1;

    @BeforeAll
    void setUp() {
        task1 = new Task1();
    }

    @Test
    void testGetIntegersFromList() {
        // Test with a list that contains integers and other objects
        List<Object> list1 = List.of(1, "two", 3.0, 4, "five");
        List<Integer> expected1 = List.of(1, 4);
        List<Integer> result1 = task1.getIntegersFromList(list1);
        assertEquals(expected1, result1);

        // Test with a list that contains only integers
        List<Integer> list2 = List.of(1, 2, 3, 4, 5);
        List<Integer> expected2 = List.of(1, 2, 3, 4, 5);
        List<Integer> result2 = task1.getIntegersFromList(list2);
        assertEquals(expected2, result2);

        // Test with an empty list
        List<Object> list3 = List.of();
        List<Integer> expected3 = List.of();
        List<Integer> result3 = task1.getIntegersFromList(list3);
        assertEquals(expected3, result3);
    }

}