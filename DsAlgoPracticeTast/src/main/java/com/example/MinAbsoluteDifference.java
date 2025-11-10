package com.example;

import java.util.Arrays;

public class MinAbsoluteDifference {
    /**
     * Returns the minimum absolute difference between any two elements in the array.
     *
     * @param arr input array of integers
     * @return minimum absolute difference
     * @throws IllegalArgumentException if array is null or has fewer than 2 elements
     */
    public static int minAbsoluteDifference(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }
        if (arr.length < 2) {
            throw new IllegalArgumentException("Array must have at least 2 elements.");
        }

        // Sort the array to ensure that the minimum difference occurs between adjacent elements
        Arrays.sort(arr);

        int minDiff = Integer.MAX_VALUE;

        // Compare adjacent elements to find the minimum absolute difference
        for (int i = 0; i < arr.length - 1; i++) {
            int diff = arr[i + 1] - arr[i];
            if (diff < minDiff) {
                minDiff = diff;
            }
        }

        return minDiff;
    }

    public static void main(String[] args) {
        // Example 1
        int[] arr1 = {3, 9, 50, 15, 99, 7};
        System.out.println("Minimum absolute difference: " + minAbsoluteDifference(arr1));
        // Expected output: 2

        // Example 2: negative numbers
        int[] arr2 = {-10, -3, -20, -15};
        System.out.println("Minimum absolute difference: " + minAbsoluteDifference(arr2));
        // Expected output: 5

        // Example 3: duplicates
        int[] arr3 = {1, 5, 3, 3, 8};
        System.out.println("Minimum absolute difference: " + minAbsoluteDifference(arr3));
        // Expected output: 0

        // Example 4: large numbers
        int[] arr4 = {1000, 500, 2000, 1500};
        System.out.println("Minimum absolute difference: " + minAbsoluteDifference(arr4));
        // Expected output: 500

        // Example 5: null input (uncomment to test exception)
        // minAbsoluteDifference(null); // Throws IllegalArgumentException
    }
}
