package com.epam.rd.autocode.collection.tree;

import java.util.Objects;
import java.util.Optional;

public class BinaryTree {

    private static final String INDENT = "-~-";
    private static final String EOL = System.lineSeparator();

    private Node root;
    private int size;

    private static final class Node {
        Integer e;
        Node left;
        Node right;

        Node(Integer e) {
            this.e = e;
        }

        public String toString() {
            return "Node[" + e + "]";
        }
    }

    // --- Constructors ---

    public BinaryTree() {
        super();
    }

    public BinaryTree(Integer... elements) {
        if (elements == null)
            throw new NullPointerException("Elements cannot be null");
        for (Integer e : elements) {
            if (e == null)
                throw new NullPointerException("Tree cannot contain null elements");
            add(e);
        }
    }

    // --- Public Methods ---

    public boolean add(Integer element) {
        if (element == null)
            throw new NullPointerException("Cannot add null element");

        if (root == null) {
            root = new Node(element);
            size++;
            return true;
        }

        Node current = root;
        while (true) {
            if (element.equals(current.e)) {
                return false; // duplicate
            } else if (element < current.e) {
                if (current.left == null) {
                    current.left = new Node(element);
                    size++;
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(element);
                    size++;
                    return true;
                }
                current = current.right;
            }
        }
    }

    public void addAll(Integer... elements) {
        if (elements == null)
            throw new NullPointerException("Elements array cannot be null");
        for (Integer e : elements) {
            if (e == null)
                throw new NullPointerException("Tree cannot contain null elements");
            add(e);
        }
    }

    public Optional<Integer> remove(Integer element) {
        if (element == null)
            throw new NullPointerException("Cannot remove null element");

        Node parent = null;
        Node current = root;

        // Find the node to remove
        while (current != null && !current.e.equals(element)) {
            parent = current;
            if (element < current.e) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null)
            return Optional.empty(); // not found

        Integer removedValue = current.e;

        // Case 1: Node has no children (leaf)
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            } else if (parent.left == current) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        // Case 2: Node has one child
        else if (current.left == null || current.right == null) {
            Node child = (current.left != null) ? current.left : current.right;
            if (current == root) {
                root = child;
            } else if (parent.left == current) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }
        // Case 3: Node has two children
        else {
            // Find leftmost node in right subtree
            Node successorParent = current;
            Node successor = current.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            // Replace current element with successor's element
            current.e = successor.e;

            // Fix the right subtree link
            if (successorParent.left == successor) {
                successorParent.left = successor.right;
            } else {
                successorParent.right = successor.right;
            }
        }

        size--;
        return Optional.of(removedValue);
    }

    public int size() {
        return size;
    }

    // --- Utility: In-order traversal for toString() ---
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        buildInOrderString(root, sb, new boolean[]{true});
        sb.append("]");
        return sb.toString();
    }

    private void buildInOrderString(Node node, StringBuilder sb, boolean[] first) {
        if (node == null) return;
        buildInOrderString(node.left, sb, first);
        if (!first[0]) sb.append(", ");
        sb.append(node.e);
        first[0] = false;
        buildInOrderString(node.right, sb, first);
    }

    private void inOrder(Node node, StringBuilder sb) {
        if (node == null)
            return;
        inOrder(node.left, sb);
        sb.append(node.e).append(", ");
        inOrder(node.right, sb);
    }

    // --- Tree String Representation (helper) ---
    String asTreeString() {
        StringBuilder sb = new StringBuilder();
        asTreeString(sb, root, 0);
        return sb.toString();
    }

    private void asTreeString(StringBuilder sb, Node node, int k) {
        if (node == null) {
            return;
        }
        asTreeString(sb, node.right, k + 1);
        sb.append(INDENT.repeat(k));
        sb.append(String.format("%3s", node.e)).append(EOL);
        asTreeString(sb, node.left, k + 1);
    }
}
