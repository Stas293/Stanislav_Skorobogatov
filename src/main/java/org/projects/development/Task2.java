package org.projects.development;

import java.util.Optional;

public class Task2 {
    public Character firstNonRepeatingLetter(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        String normalized = str.toLowerCase();

        Optional<Character> result = str.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> normalized.indexOf(Character.toLowerCase(c)) ==
                        normalized.lastIndexOf(Character.toLowerCase(c)))
                .findFirst();

        return result.orElse(null);
    }
}
