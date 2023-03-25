package org.projects.development;

import java.util.Collections;
import java.util.List;

public class Task1 {
    public List<Integer> getIntegersFromList(List<?> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        return list.parallelStream()
                .filter(Integer.class::isInstance)
                .map(i -> (Integer) i)
                .toList();
    }
}
