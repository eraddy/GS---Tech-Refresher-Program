package com.epam.rd.autocode.dllist;

import java.util.Optional;

public class DoublyLinkedListImpl implements DoublyLinkedList {

    private Node head;
    private Node tail;

    private static class Node {
        Object element;
        Node next;
        Node prev;

        Node(Object element, Node prev, Node next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    @Override
    public boolean addFirst(Object element) {
        if (element == null) return false;

        Node newNode = new Node(element, null, head);

        if (head != null) {
            head.prev = newNode;
        }
        head = newNode;

        if (tail == null) {
            tail = newNode;
        }
        return true;
    }

    @Override
    public boolean addLast(Object element) {
        if (element == null) return false;

        Node newNode = new Node(element, tail, null);

        if (tail != null) {
            tail.next = newNode;
        }
        tail = newNode;

        if (head == null) {
            head = newNode;
        }
        return true;
    }

    @Override
    public void delete(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        Node current = head;
        int i = 0;
        while (current != null && i < index) {
            current = current.next;
            i++;
        }

        if (current == null) return;

        Node prevNode = current.prev;
        Node nextNode = current.next;

        if (prevNode != null) {
            prevNode.next = nextNode;
        } else {
            head = nextNode;
        }

        if (nextNode != null) {
            nextNode.prev = prevNode;
        } else {
            tail = prevNode;
        }
    }

    @Override
    public Optional<Object> remove(Object element) {
        if (element == null) return Optional.empty();

        Node current = head;
        while (current != null) {
            if (current.element.equals(element)) {
                Object removed = current.element;

                Node prevNode = current.prev;
                Node nextNode = current.next;

                if (prevNode != null) {
                    prevNode.next = nextNode;
                } else {
                    head = nextNode;
                }

                if (nextNode != null) {
                    nextNode.prev = prevNode;
                } else {
                    tail = prevNode;
                }

                return Optional.of(removed);
            }
            current = current.next;
        }
        return Optional.empty();
    }

    @Override
    public boolean set(int index, Object element) throws IndexOutOfBoundsException {
        if (element == null) return false;
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();

        Node current = head;
        int i = 0;
        while (current != null && i < index) {
            current = current.next;
            i++;
        }

        if (current != null) {
            current.element = element;
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    @Override
    public Object[] toArray() {
        int size = size();
        Object[] arr = new Object[size];
        Node current = head;
        int i = 0;
        while (current != null) {
            arr[i++] = current.element;
            current = current.next;
        }
        return arr;
    }

    @Override
    public String toString() {
        if (head == null) return "";
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.element);
            if (current.next != null) sb.append(" ");
            current = current.next;
        }
        return sb.toString();
    }
}
