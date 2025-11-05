package com.example;

interface LinkedList {
    void printValue();
    LinkedList getNext();
}

/**
 * ImmutableLinkedList represents an immutable node in a singly linked list.
 * Once created, its value and next reference cannot be changed.
 */
final class ImmutableLinkedList implements LinkedList {
    private final int value;
    private final LinkedList next;

    ImmutableLinkedList(int value, LinkedList next) {
        this.value = value;
        this.next = next;
    }

    @Override
    public void printValue() {
        System.out.println(value);
    }

    @Override
    public LinkedList getNext() {
        return next;
    }
}

/**
 * Utility class that provides methods to operate on immutable linked lists.
 */
public class ImmutableLinkedListInReverse {

    /**
     * Prints the linked list in reverse order using recursion.
     * This method operates only through the provided LinkedList interface.
     *
     * @param head The head of the linked list (can be null)
     */
    public void printLinkedListInReverse(LinkedList head) {
        if (head == null) return; // Handle null or empty list
        printLinkedListInReverse(head.getNext());
        head.printValue();
    }

    /**
     * Alternative iterative version to handle very large lists
     * and avoid potential StackOverflowError.
     */
    public void printLinkedListInReverseIterative(LinkedList head) {
        java.util.Stack<LinkedList> stack = new java.util.Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.getNext();
        }
        while (!stack.isEmpty()) {
            stack.pop().printValue();
        }
    }

    /**
     * Test cases demonstrating the reverse printing functionality.
     */
    public static void main(String[] args) {
        ImmutableLinkedList empty = null;
        ImmutableLinkedList single = new ImmutableLinkedList(42, null);
        ImmutableLinkedList node3 = new ImmutableLinkedList(3, null);
        ImmutableLinkedList node2 = new ImmutableLinkedList(2, node3);
        ImmutableLinkedList node1 = new ImmutableLinkedList(1, node2);

        ImmutableLinkedListInReverse util = new ImmutableLinkedListInReverse();

        System.out.println("Test 1: Null input");
        util.printLinkedListInReverse(empty);

        System.out.println("\nTest 2: Single node");
        util.printLinkedListInReverse(single);

        System.out.println("\nTest 3: Multi-node (Recursive)");
        util.printLinkedListInReverse(node1);

        System.out.println("\nTest 4: Multi-node (Iterative)");
        util.printLinkedListInReverseIterative(node1);
    }
}
