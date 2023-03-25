package org.projects.development;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Task5 {
    public String meeting(String s) {
        String[] names = s.split(";");
        if (names[0].split(":").length < 2) {
            return "";
        }
        return Arrays.stream(names)
                .map(String::toUpperCase)
                .sorted(Comparator.comparing((String name) -> name.split(":")[1])
                        .thenComparing((String name) -> name.split(":")[0]))
                .map(name -> "(" + name.split(":")[1] + ", " + name.split(":")[0] + ")")
                .collect(Collectors.joining());
    }
}
