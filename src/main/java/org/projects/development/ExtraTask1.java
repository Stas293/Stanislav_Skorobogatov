package org.projects.development;

import java.util.Arrays;

public class ExtraTask1 {

    public int nextBigger(int num) {
        if (num <= 0) {
            return -1;
        }
        int[] digits = Integer.toString(num).chars()
                .map(Character::getNumericValue)
                .toArray();

        for (int i = digits.length - 1; i > 0; i--) {
            if (digits[i] > digits[i - 1]) {
                swap(digits, i);
                return Arrays.stream(digits)
                        .reduce(0,
                                (a, b) -> a * 10 + b);
            }
        }
        return -1;
    }

    private static void swap(int[] digits, int i) {
        int temp = digits[i];
        digits[i] = digits[i - 1];
        digits[i - 1] = temp;
    }
}
