package com.epam.rd.autocode.collection.array;

import java.util.Optional;

public class SimpleArrayListImpl implements SimpleArrayList {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASE_LOAD_FACTOR = 0.75;
    private static final double DECREASE_LOAD_FACTOR = 0.4;

    private Object[] elements;
    private int size;

    public SimpleArrayListImpl() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public SimpleArrayListImpl(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Capacity can not be 0 or negative");
        }
        this.elements = new Object[initialCapacity];
        this.size = 0;
    }

    @Override
    public boolean add(Object element) {
        if (element == null) {
            throw new NullPointerException("Null elements are not allowed");
        }

        // anticipate the insertion: if adding one more element would exceed 75% load, grow now
        if ((size + 1) > elements.length * INCREASE_LOAD_FACTOR) {
            increaseCapacity();
        }

        elements[size++] = element;
        return true;
    }

    // increase according to given formula: newCapacity = (int)(currentCapacity * 2 * loadFactor)
    private void increaseCapacity() {
        int currentCapacity = elements.length;
        int newCapacity = (int) (currentCapacity * 2 * INCREASE_LOAD_FACTOR);
        if (newCapacity <= currentCapacity) {
            // fallback (shouldn't normally happen), ensure capacity grows at least by 1
            newCapacity = currentCapacity + 1;
        }
        Object[] newElements = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    @Override
    public int capacity() {
        return elements.length;
    }

    @Override
    public boolean decreaseCapacity() {
        // Decrease if 40% or less full
        if (size <= elements.length * DECREASE_LOAD_FACTOR) {
            int newCapacity = size * 2;
            if (newCapacity == 0) {
                newCapacity = DEFAULT_CAPACITY;
            }

            if (newCapacity < elements.length) {
                Object[] newElements = new Object[newCapacity];
                for (int i = 0; i < size; i++) {
                    newElements[i] = elements[i];
                }
                elements = newElements;
                return true;
            }
        }
        return false;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return elements[index];
    }

    private boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Optional<Object> remove(Object el) {
        if (el == null) {
            throw new NullPointerException("Cannot remove null element");
        }

        if (isEmpty()) {
            return Optional.empty();
        }

        for (int i = 0; i < size; i++) {
            // use equals to compare (elements are non-null)
            if (elements[i].equals(el)) {
                Object removed = elements[i];
                // shift left
                for (int j = i; j < size - 1; j++) {
                    elements[j] = elements[j + 1];
                }
                elements[--size] = null; // avoid memory leak
                // do not auto-decrease here per tests; decreaseCapacity() may be called externally or here as needed
                return Optional.of(removed);
            }
        }
        return Optional.empty();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
