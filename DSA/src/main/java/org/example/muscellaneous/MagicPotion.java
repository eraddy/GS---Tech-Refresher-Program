package org.example.muscellaneous;

public class MagicPotion {
    private static Integer minimalSteps(String ingredients) {
        int n = ingredients.length();
        if (n == 0) return 0;

        int minSteps = n; // worst case

        for (int i = 1; i <= n / 2; i++) {
            // Check if prefix of length i repeats immediately after
            String prefix = ingredients.substring(0, i);
            if (ingredients.startsWith(prefix, i)) {
                // Encoded length = prefix + '*' + remaining part
                int encodedLength = i + 1 + (n - 2 * i);
                if (encodedLength < minSteps) {
                    minSteps = encodedLength;
                }
            }
        }
        return minSteps;
    }
}
