package org.example.muscellaneous;

import java.util.HashMap;
import java.util.Map;

public class BestAverageGrade {
    public static Integer bestAverageGrade(String[][] scores) {
        if (scores == null || scores.length == 0) {
            return 0;
        }

        Map<String, Integer> sumMap = new HashMap<>();
        Map<String, Integer> countMap = new HashMap<>();

        // Step 1: Aggregate sum and count per student
        for (String[] entry : scores) {
            String name = entry[0];
            int score = Integer.parseInt(entry[1]);

            sumMap.put(name, sumMap.getOrDefault(name, 0) + score);
            countMap.put(name, countMap.getOrDefault(name, 0) + 1);
        }

        // Step 2: Compute max average
        int bestAvg = Integer.MIN_VALUE;
        for (String name : sumMap.keySet()) {
            int total = sumMap.get(name);
            int count = countMap.get(name);
            int avg = total / count; // automatically floored
            bestAvg = Math.max(bestAvg, avg);
        }

        return bestAvg;
    }
}
