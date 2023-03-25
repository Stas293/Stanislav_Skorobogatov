package org.projects.development;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Task4Test {
    private Task4 task4;

    @BeforeAll
    void setUp() {
        task4 = new Task4();
    }

    @Test
    void testCountPairs() {
        int[] arr1 = {1, 3, 6, 2, 2, 0, 4, 5};
        int target1 = 5;
        assertEquals(4, task4.countPairs(arr1, target1));

        int[] arr2 = {1, 3, 6, 2, 2, 0, 4, 5};
        int target2 = 7;
        assertEquals(4, task4.countPairs(arr2, target2));

        int[] arr3 = {1, 3, 6, 2, 2, 0, 4, 5};
        int target3 = 20;
        assertEquals(0, task4.countPairs(arr3, target3));

        int[] arr4 = {2, 2};
        int target4 = 4;
        assertEquals(1, task4.countPairs(arr4, target4));

        int[] arr5 = {};
        int target5 = 5;
        assertEquals(0, task4.countPairs(arr5, target5));

        int[] arr6 = {1};
        int target6 = 1;
        assertEquals(0, task4.countPairs(arr6, target6));

        int[] arr7 = {1, 2, 3};
        int target7 = 2;
        assertEquals(0, task4.countPairs(arr7, target7));

        int[] arr8 = {1, 2, 3};
        int target8 = 4;
        assertEquals(1, task4.countPairs(arr8, target8));
    }

    @Test
    void testCountPairsStream() {
        int[] arr1 = {1, 3, 6, 2, 2, 0, 4, 5};
        int target1 = 5;
        assertEquals(4, task4.countPairs(arr1, target1));

        int[] arr2 = {1, 3, 6, 2, 2, 0, 4, 5};
        int target2 = 5;
        assertEquals(4, task4.countPairsStream(arr2, target2));

        int[] arr3 = {1, 2, 3, 4, 5};
        int target3 = 7;
        assertEquals(2, task4.countPairsStream(arr3, target3));

        int[] arr4 = {1, 1, 1, 1};
        int target4 = 2;
        assertEquals(6, task4.countPairsStream(arr4, target4));

        int[] arr5 = {0, 0, 0};
        int target5 = 0;
        assertEquals(3, task4.countPairsStream(arr5, target5));
    }
}