package org.example.tree;

import java.util.*;

public class LargestTree {
    public static Integer largestTree(final Map<Integer, Integer> immediateParent) {
        if (immediateParent == null || immediateParent.isEmpty()) {
            return null;
        }

        // Step 1: Identify all nodes
        Set<Integer> allNodes = new HashSet<>();
        Set<Integer> children = new HashSet<>();

        for (Map.Entry<Integer, Integer> entry : immediateParent.entrySet()) {
            int child = entry.getKey();
            int parent = entry.getValue();
            allNodes.add(child);
            allNodes.add(parent);
            children.add(child);
        }

        // Step 2: Find roots (nodes that are never children)
        Set<Integer> roots = new HashSet<>(allNodes);
        roots.removeAll(children);

        // Step 3: Build parent -> children map
        Map<Integer, List<Integer>> parentToChildren = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : immediateParent.entrySet()) {
            int child = entry.getKey();
            int parent = entry.getValue();
            parentToChildren
                    .computeIfAbsent(parent, k -> new ArrayList<>())
                    .add(child);
        }

        // Step 4: For each root, count tree size
        int maxSize = 0;
        int minRoot = Integer.MAX_VALUE;

        for (int root : roots) {
            int size = countSize(root, parentToChildren);
            if (size > maxSize || (size == maxSize && root < minRoot)) {
                maxSize = size;
                minRoot = root;
            }
        }

        return minRoot;
    }

    private static int countSize(int node, Map<Integer, List<Integer>> tree) {
        int count = 1; // count current node
        if (tree.containsKey(node)) {
            for (int child : tree.get(node)) {
                count += countSize(child, tree);
            }
        }
        return count;
    }

    // --- TESTING ---
    public static void main(String[] args) {
        Map<Integer, Integer> input1 = new HashMap<>();
        input1.put(1, 2);
        input1.put(3, 4);
        System.out.println(largestTree(input1)); // Expected 2

        Map<Integer, Integer> input2 = new HashMap<>();
        input2.put(1, 2);
        input2.put(2, 3);
        input2.put(4, 5);
        System.out.println(largestTree(input2)); // Expected 3 (tree of size 3: 1→2→3)
    }
}
