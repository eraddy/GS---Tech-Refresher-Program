package org.example.muscellaneous;

import java.util.HashSet;

public class UniqueTuples {
    public static HashSet<String> uniqueTuples(String input, int len) {
        HashSet<String> result = new HashSet<>();

        if (input == null || len <= 0 || len > input.length()) {
            return result;
        }

        for (int i = 0; i <= input.length() - len; i++) {
            String substring = input.substring(i, i + len);
            result.add(substring);
        }

        return result;
    }
}
