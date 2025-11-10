package com.epam.rd.autotasks;

import java.util.*;

public class Words {

    public String countWords(List<String> lines) {
        Map<String, Integer> countMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        // Count all words, case-insensitive, split by non-letter chars
        for (String line : lines) {
            String[] words = line.split("[^\\p{L}\\p{N}]+");
            for (String w : words) {
                if (w.length() == 0) continue;
                String word = w.toLowerCase();
                countMap.put(word, countMap.getOrDefault(word, 0) + 1);
            }
        }

        // Filter valid entries
        List<Map.Entry<String, Integer>> entries = new ArrayList<>();
        for (Map.Entry<String, Integer> e : countMap.entrySet()) {
            if (e.getKey().length() >= 4 && e.getValue() >= 10) {
                entries.add(e);
            }
        }

        // Sort: by count desc, then word asc
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                int cmp = b.getValue().compareTo(a.getValue());
                if (cmp != 0) return cmp;
                return a.getKey().compareTo(b.getKey());
            }
        });

        // Build result
        for (Map.Entry<String, Integer> e : entries) {
            sb.append(e.getKey()).append(" - ").append(e.getValue()).append("\n");
        }

        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '\n') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();

    }
}
