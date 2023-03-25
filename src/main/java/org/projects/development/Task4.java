package org.projects.development;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Task4 {
    public long countPairs(int[] arr, int target) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        Map<Integer, Integer> count = new HashMap<>();
        int pairs = 0;
        for (int num : arr) {
            int complement = target - num;
            if (count.containsKey(complement)) {
                pairs += count.get(complement);
            }
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        return pairs;
    }

    public long countPairsStream(int[] arr, int target) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return IntStream.range(0, arr.length)
                .flatMap(i -> IntStream.range(i+1, arr.length)
                        .filter(j -> arr[i] + arr[j] == target))
                .count();
    }
}
