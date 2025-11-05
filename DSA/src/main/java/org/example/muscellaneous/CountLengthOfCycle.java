package org.example.muscellaneous;

public class CountLengthOfCycle {
    public static int countLengthofCycle(int[] arr, int startIndex) {
        if (arr == null || arr.length == 0 || startIndex < 0 || startIndex >= arr.length)
            return -1;

        int slow = startIndex;
        int fast = startIndex;

        // Detect cycle
        while (true) {
            // Move slow one step
            slow = arr[slow];

            // Move fast two steps (check bounds)
            fast = arr[fast];
            if (fast < 0 || fast >= arr.length) return -1;
            fast = arr[fast];
            if (fast < 0 || fast >= arr.length) return -1;

            // If slow == fast, cycle found
            if (slow == fast) break;
        }

        // Find cycle length
        int count = 1;
        int temp = arr[slow];
        while (temp != slow) {
            temp = arr[temp];
            count++;
        }

        return count;
    }
}
