package org.projects.development;

public class Task3 {
    public int digitalRoot(int n) {
        if (n < 0) {
            return -1;
        }
        int sum = Integer.toString(n).chars()
                .map(Character::getNumericValue)
                .sum();

        return sum < 10 ? sum : digitalRoot(sum);
    }
}
