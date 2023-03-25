package org.projects.development;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Task5Test {
    private Task5 task5;

    @BeforeAll
    void setUp() {
        task5 = new Task5();
    }

    @Test
    void testMeeting() {
        String s1 = "";
        String s2 = "John:Doe";
        String s3 = "Fred:Corwill;Wilfred:Corwill;Barney:Tornbull;Betty:Tornbull;Bjon:Tornbull;Raphael:Corwill;Alfred:Corwill";
        String s4 = "John:Doe;Jane:Doe;James:Smith;John:Doe";
        String s5 = "John:Doe;JANE:doe;James:smith";

        String result1 = task5.meeting(s1);
        String result2 = task5.meeting(s2);
        String result3 = task5.meeting(s3);
        String result4 = task5.meeting(s4);
        String result5 = task5.meeting(s5);

        assertEquals("", result1);
        assertEquals("(DOE, JOHN)", result2);
        assertEquals("(CORWILL, ALFRED)(CORWILL, FRED)(CORWILL, RAPHAEL)(CORWILL, WILFRED)(TORNBULL, BARNEY)(TORNBULL, BETTY)(TORNBULL, BJON)", result3);
        assertEquals("(DOE, JANE)(DOE, JOHN)(DOE, JOHN)(SMITH, JAMES)", result4);
        assertEquals("(DOE, JANE)(DOE, JOHN)(SMITH, JAMES)", result5);
    }

}