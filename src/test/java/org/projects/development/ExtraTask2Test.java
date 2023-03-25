package org.projects.development;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExtraTask2Test {
    private ExtraTask2 extraTask2;

    @BeforeAll
    void setUp() {
        extraTask2 = new ExtraTask2();
    }

    @Test
    void testLongToIP() {
        // Test cases with valid input
        assertEquals("128.32.10.1", extraTask2.longToIP(2149583361L));
        assertEquals("0.0.0.0", extraTask2.longToIP(0));
        assertEquals("255.255.255.255", extraTask2.longToIP(4294967295L));
        assertEquals("10.0.0.1", extraTask2.longToIP(167772161));

        // Test cases with invalid input
        assertEquals("Invalid input", extraTask2.longToIP(-1));
        assertEquals("Invalid input", extraTask2.longToIP(4294967296L));
    }
}