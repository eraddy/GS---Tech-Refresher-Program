package com.example;

import java.util.PriorityQueue;

public class MinimumCostToConnectSticks {
    /**
     * Returns the minimum cost to connect all sticks into one.
     *
     * @param sticks array of stick lengths
     * @return minimum cost
     */
    public static int connectSticks(int[] sticks) {
        if (sticks == null || sticks.length == 0) {
            return 0; // No sticks, cost is 0
        }

        if (sticks.length == 1) {
            return 0; // Only one stick, no cost
        }

        // Use a min-heap to always get the two smallest sticks
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int stick : sticks) {
            minHeap.offer(stick);
        }

        int totalCost = 0;

        // Combine sticks until only one remains
        while (minHeap.size() > 1) {
            int first = minHeap.poll();
            int second = minHeap.poll();
            int cost = first + second;
            totalCost += cost;
            minHeap.offer(cost); // Add the new stick back to the heap
        }

        return totalCost;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        int[] sticks1 = {2, 4, 3};
        System.out.println("Minimum cost: " + connectSticks(sticks1));
        // Expected Output: 14

        int[] sticks2 = {1, 8, 3, 5};
        System.out.println("Minimum cost: " + connectSticks(sticks2));
        // Expected Output: 30

        int[] sticks3 = {5};
        System.out.println("Minimum cost: " + connectSticks(sticks3));
        // Expected Output: 0

        int[] sticks4 = {};
        System.out.println("Minimum cost: " + connectSticks(sticks4));
        // Expected Output: 0
    }
}
