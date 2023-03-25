package org.projects.development;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExtraTask1Test {
    private ExtraTask1 extraTask1;

    @BeforeAll
    void setUp() {
        extraTask1 = new ExtraTask1();
    }

    @Test
    void testNextBigger() {
        // Test cases with next bigger number
        assertEquals(21, extraTask1.nextBigger(12));
        assertEquals(531, extraTask1.nextBigger(513));
        assertEquals(2071, extraTask1.nextBigger(2017));
        assertEquals(123456798, extraTask1.nextBigger(123456789));

        // Test cases with no next bigger number
        assertEquals(-1, extraTask1.nextBigger(21));
        assertEquals(-1, extraTask1.nextBigger(531));
        assertEquals(-1, extraTask1.nextBigger(321));
        assertEquals(-1, extraTask1.nextBigger(987654321));

        // Test cases with invalid input
        assertEquals(-1, extraTask1.nextBigger(0));
        assertEquals(-1, extraTask1.nextBigger(-123));
    }

}