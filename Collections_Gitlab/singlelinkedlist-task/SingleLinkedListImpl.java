package com.epam.rd.autocode.collection.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public class SingleLinkedListImpl implements List {

    private Node head;

    private static class Node {
        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
        }

        Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return "[" + data + ']';
        }
    }

    public SingleLinkedListImpl() {
        head = new Node(0, null); // header node, data stores size
    }

    @Override
    public void clear() {
        head.next = null;
        head.data = 0;
    }

    @Override
    public int size() {
        return (int) head.data;
    }

    @Override
    public boolean add(Object el) {
        Objects.requireNonNull(el, "Null elements are not allowed");
        head.next = new Node(el, head.next);
        head.data = (int) head.data + 1;
        return true;
    }

    @Override
    public Optional<Object> remove(Object el) {
        Objects.requireNonNull(el, "Null elements are not allowed");
        Node prev = head;
        Node curr = head.next;

        while (curr != null) {
            if (Objects.equals(curr.data, el)) {
                prev.next = curr.next;
                head.data = (int) head.data - 1;
                return Optional.of(curr.data);
            }
            prev = curr;
            curr = curr.next;
        }
        return Optional.empty();
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= (int) head.data) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        Node curr = head.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.data;
    }

    @Override
    public String toString() {
        if (head.next == null) return "[]";

        StringBuilder sb = new StringBuilder("[");
        Node curr = head.next;
        while (curr != null) {
            sb.append(curr.data);
            if (curr.next != null) sb.append(", ");
            curr = curr.next;
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<Object> iterator() {
        return new Iterator<Object>() {
            private Node prev = head;
            private Node curr = head.next;
            private Node lastReturned = null;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public Object next() {
                if (curr == null) {
                    throw new NoSuchElementException();
                }
                lastReturned = curr;
                prev = prev == head ? head : prev.next;
                curr = curr.next;
                return lastReturned.data;
            }

            @Override
            public void remove() {
                if (lastReturned == null) {
                    throw new IllegalStateException();
                }

                Node before = head;
                Node temp = head.next;
                while (temp != null && temp != lastReturned) {
                    before = temp;
                    temp = temp.next;
                }

                if (temp == null) {
                    throw new IllegalStateException();
                }

                before.next = temp.next;
                head.data = (int) head.data - 1;
                lastReturned = null;
            }
        };
    }
}
