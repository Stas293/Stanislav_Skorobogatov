package org.projects.development;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Task2Test {
    private Task2 task2;

    @BeforeAll
    void setUp() {
        task2 = new Task2();
    }

    @Test
    void testFirstNonRepeatingLetter() {
        // Test with a string that contains non-repeating characters
        String str1 = "abcde";
        char expected1 = 'a';
        char result1 = task2.firstNonRepeatingLetter(str1);
        assertEquals(expected1, result1);

        // Test with a string that contains only one non-repeating character
        String str2 = "aAbbc";
        char expected2 = 'c';
        char result2 = task2.firstNonRepeatingLetter(str2);
        assertEquals(expected2, result2);

        // Test with a string that contains only repeating characters
        String str3 = "aaaaa";
        // Expect null since there are no non-repeating characters
        Character result3 = task2.firstNonRepeatingLetter(str3);
        assertNull(result3);

        // Test with a string that contains upper- and lowercase characters
        String str4 = "sTreSS";
        char expected4 = 'T';
        char result4 = task2.firstNonRepeatingLetter(str4);
        assertEquals(expected4, result4);

        // Test with a string that contains non-alphabetic characters
        String str5 = "@#$%^&*()_-+=~`|}{[]:;?><,./@";
        char expected5 = '#';
        char result5 = task2.firstNonRepeatingLetter(str5);
        assertEquals(expected5, result5);

        // Test with an empty string
        String str6 = "";
        // Expect null character since there are no non-repeating characters
        Character result6 = task2.firstNonRepeatingLetter(str6);
        assertNull(result6);

        // Test with a string that contains space characters
        String str7 = "a b c d e";
        char expected7 = 'a';
        char result7 = task2.firstNonRepeatingLetter(str7);
        assertEquals(expected7, result7);
    }

}