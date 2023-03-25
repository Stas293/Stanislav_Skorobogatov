package org.projects.development;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Task3Test {
    private Task3 task3;

    @BeforeAll
    void setUp() {
        task3 = new Task3();
    }

    @Test
    void testDigitalRoot() {
        assertEquals(7, task3.digitalRoot(16));
        assertEquals(2, task3.digitalRoot(38));
        assertEquals(6, task3.digitalRoot(123));
        assertEquals(9, task3.digitalRoot(999_999));
        assertEquals(0, task3.digitalRoot(0));
        assertEquals(6, task3.digitalRoot(888));
        assertEquals(1, task3.digitalRoot(1));
        assertEquals(2, task3.digitalRoot(23_456));
    }
}