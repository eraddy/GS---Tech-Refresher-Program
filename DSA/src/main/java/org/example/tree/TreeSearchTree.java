package org.example.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeSearchTree {
    static class BST {
        private Node root;

        public BST() {
            this.root = null; // no dummy node — start empty
        }

        // Insert a value into the BST
        public void put(int value) {
            root = put(root, value);
        }

        // Recursive helper for insertion
        private Node put(Node node, int value) {
            if (node == null) {
                Node newNode = new Node();
                newNode.val = value;
                return newNode;
            }

            if (value < node.val) {
                node.left = put(node.left, value);
            } else if (value > node.val) {
                node.right = put(node.right, value);
            }
            // If equal, do nothing (no duplicates)
            return node;
        }

        // Check if a value exists in BST
        public boolean contains(int value) {
            return contains(root, value);
        }

        private boolean contains(Node node, int value) {
            if (node == null) return false;

            if (value == node.val) return true;
            if (value < node.val) return contains(node.left, value);
            else return contains(node.right, value);
        }

        // Return sorted values (in-order)
        public List<Integer> inOrderTraversal() {
            final ArrayList<Integer> acc = new ArrayList<>();
            inOrderTraversal(root, acc);
            return acc;
        }

        // Fixed traversal — in-order: left, node, right
        private void inOrderTraversal(Node node, List<Integer> acc) {
            if (node == null) return;
            inOrderTraversal(node.left, acc);
            acc.add(node.val);
            inOrderTraversal(node.right, acc);
        }

        // Node structure
        private static class Node {
            Integer val;
            Node left;
            Node right;
        }
    }

    // --------------------- TESTS ---------------------
    public static void testBST() {
        final BST searchTree = new BST();
        searchTree.put(3);
        searchTree.put(1);
        searchTree.put(2);
        searchTree.put(5);

        assertFalse(searchTree.contains(0));
        assertTrue(searchTree.contains(1));
        assertTrue(searchTree.contains(5));
        assertFalse(searchTree.contains(6));

        assertEquals(Arrays.asList(1, 2, 3, 5), searchTree.inOrderTraversal());
    }

    private static void assertFalse(boolean rez) {
        if (rez) throw new RuntimeException("Test failed");
        else System.out.println("Test passed");
    }

    private static void assertTrue(boolean rez) {
        if (!rez) throw new RuntimeException("Test failed");
        else System.out.println("Test passed");
    }

    private static void assertEquals(List<Integer> expected, List<Integer> result) {
        if (!expected.equals(result)) {
            System.out.printf("Test failed \"%s\" not equal to \"%s\"%n", expected, result);
        } else {
            System.out.println("Test passed");
        }
    }

    // Additional tests for robustness
    public static void extraTests() {
        BST bst = new BST();
        bst.put(10);
        bst.put(5);
        bst.put(15);
        bst.put(3);
        bst.put(7);
        bst.put(12);
        bst.put(17);

        assertTrue(bst.contains(7));
        assertFalse(bst.contains(100));
        assertEquals(Arrays.asList(3, 5, 7, 10, 12, 15, 17), bst.inOrderTraversal());
    }

    public static void main(String[] args) {
        testBST();
        extraTests();
    }
}
