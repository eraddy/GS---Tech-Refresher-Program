package com.example;

import java.util.Arrays;

public class MergeSortedArrays {

    /**
     * Merges two sorted arrays into a single sorted array.
     *
     * @param arr1 first sorted array
     * @param arr2 second sorted array
     * @return merged sorted array
     * @throws IllegalArgumentException if either input array is null
     */
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            throw new IllegalArgumentException("Input arrays cannot be null.");
        }

        int n1 = arr1.length;
        int n2 = arr2.length;

        // If both arrays are empty, return an empty array
        if (n1 == 0 && n2 == 0) {
            return new int[0];
        }

        int[] merged = new int[n1 + n2];
        int i = 0, j = 0, k = 0;

        // Merge arrays using two-pointer technique
        while (i < n1 && j < n2) {
            if (arr1[i] <= arr2[j]) {
                merged[k++] = arr1[i++];
            } else {
                merged[k++] = arr2[j++];
            }
        }

        // Copy any remaining elements from arr1
        while (i < n1) {
            merged[k++] = arr1[i++];
        }

        // Copy any remaining elements from arr2
        while (j < n2) {
            merged[k++] = arr2[j++];
        }

        return merged;
    }

    public static void main(String[] args) {
        // Example 1
        int[] arr1 = {1, 3, 4, 5};
        int[] arr2 = {2, 4, 6, 8};
        System.out.println("Merged array: " + Arrays.toString(mergeSortedArrays(arr1, arr2)));
        // Expected Output: [1, 2, 3, 4, 4, 5, 6, 8]

        // Example 2
        int[] arr3 = {5, 8, 9};
        int[] arr4 = {4, 7, 8};
        System.out.println("Merged array: " + Arrays.toString(mergeSortedArrays(arr3, arr4)));
        // Expected Output: [4, 5, 7, 8, 8, 9]

        // Edge case: one empty array
        int[] arr5 = {};
        int[] arr6 = {1, 2, 3};
        System.out.println("Merged array: " + Arrays.toString(mergeSortedArrays(arr5, arr6)));
        // Expected Output: [1, 2, 3]

        // Edge case: both empty arrays
        int[] arr7 = {};
        int[] arr8 = {};
        System.out.println("Merged array: " + Arrays.toString(mergeSortedArrays(arr7, arr8)));
        // Expected Output: []

        // Edge case: null array (uncomment to test exception)
        // mergeSortedArrays(null, arr2); // Throws IllegalArgumentException
    }
}
