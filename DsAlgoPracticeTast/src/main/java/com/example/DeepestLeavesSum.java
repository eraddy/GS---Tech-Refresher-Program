package com.example;


import java.util.Queue;
import java.util.LinkedList;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class DeepestLeavesSum {
    /**
     * Returns the sum of values of the deepest leaves in the binary tree.
     *
     * @param root root of the binary tree
     * @return sum of deepest leaves
     */
    public int deepestLeavesSum(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int sum = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            sum = 0; // Reset sum for the current level

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        // sum of the last level (deepest leaves) is returned
        return sum;
    }

    // Main method for testing
    public static void main(String[] args) {
        DeepestLeavesSum solver = new DeepestLeavesSum();

        // Example 1
        TreeNode root1 = new TreeNode(1,
                new TreeNode(2, new TreeNode(4, new TreeNode(7), null), new TreeNode(5)),
                new TreeNode(3, null, new TreeNode(6, null, new TreeNode(8)))
        );
        System.out.println("Deepest leaves sum: " + solver.deepestLeavesSum(root1));
        // Expected Output: 15

        // Example 2
        TreeNode root2 = new TreeNode(6,
                new TreeNode(7, new TreeNode(2, new TreeNode(9), null), new TreeNode(7, new TreeNode(1), new TreeNode(4))),
                new TreeNode(8, new TreeNode(1), new TreeNode(3, null, new TreeNode(5)))
        );
        System.out.println("Deepest leaves sum: " + solver.deepestLeavesSum(root2));
        // Expected Output: 19
    }
}
