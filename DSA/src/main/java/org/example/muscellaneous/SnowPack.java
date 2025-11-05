package org.example.muscellaneous;

public class SnowPack {         // similar to the leetcode question trapping rain water
    public static Integer computeSnowpack(Integer[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int n = arr.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        // Fill leftMax array
        leftMax[0] = arr[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
        }

        // Fill rightMax array
        rightMax[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], arr[i]);
        }

        // Calculate total water trapped
        int total = 0;
        for (int i = 0; i < n; i++) {
            int water = Math.min(leftMax[i], rightMax[i]) - arr[i];
            if (water > 0)
                total += water;
        }

        return total;
    }
}
