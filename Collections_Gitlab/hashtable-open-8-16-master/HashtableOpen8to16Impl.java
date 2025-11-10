package com.epam.rd.autocode.hashtableopen816;

public class HashtableOpen8to16Impl implements HashtableOpen8to16 {

    private static final int MIN_CAPACITY = 2;
    private static final int INITIAL_CAPACITY = 8;
    private static final int MAX_CAPACITY = 16;

    private int[] keys;
    private Object[] values;
    private byte[] states; // 0=EMPTY, 1=OCCUPIED, 2=DELETED
    private int size;
    private int capacity;

    public HashtableOpen8to16Impl() {
        capacity = INITIAL_CAPACITY;
        keys = new int[capacity];
        values = new Object[capacity];
        states = new byte[capacity];
        size = 0;
    }

    @Override
    public void insert(int key, Object value) {
        if (value == null)
            throw new IllegalArgumentException("Value cannot be null");

        // Resize if table is full
        if (size == capacity) {
            if (capacity == MAX_CAPACITY) throw new IllegalStateException();
            resize(capacity * 2);
        }

        int index = findKeyIndex(key);
        if (index >= 0) { // key already exists, replace
            values[index] = value;
            return;
        }

        // Insert into first available slot (EMPTY or DELETED)
        int hash = (key & 0x7fffffff) % capacity;
        int firstDeleted = -1;
        for (int i = 0; i < capacity; i++) {
            int idx = (hash + i) % capacity;
            if (states[idx] == 1 && keys[idx] == key) {
                values[idx] = value;
                return;
            } else if (states[idx] == 2 && firstDeleted == -1) {
                firstDeleted = idx;
            } else if (states[idx] == 0) {
                if (firstDeleted != -1) idx = firstDeleted;
                keys[idx] = key;
                values[idx] = value;
                states[idx] = 1;
                size++;
                return;
            }
        }

        // Full and cannot insert
        if (capacity == MAX_CAPACITY)
            throw new IllegalStateException();
        else
            resize(capacity * 2);
        insert(key, value);
    }

    @Override
    public Object search(int key) {
        int hash = (key & 0x7fffffff) % capacity;
        for (int i = 0; i < capacity; i++) {
            int idx = (hash + i) % capacity;
            if (states[idx] == 0) return null;
            if (states[idx] == 1 && keys[idx] == key) return values[idx];
        }
        return null;
    }

    @Override
    public void remove(int key) {
        int hash = (key & 0x7fffffff) % capacity;
        for (int i = 0; i < capacity; i++) {
            int idx = (hash + i) % capacity;
            if (states[idx] == 0) return; // not found
            if (states[idx] == 1 && keys[idx] == key) {
                states[idx] = 2; // mark deleted
                values[idx] = null;
                size--;
                break;
            }
        }

        // Resize down if load factor ≤ 1/4 and size > 0
        if (size > 0 && size <= capacity / 4) {
            int newCap = capacity / 2;
            if (newCap >= MIN_CAPACITY) resize(newCap);
        }
    }

    private int findKeyIndex(int key) {
        int hash = (key & 0x7fffffff) % capacity;
        for (int i = 0; i < capacity; i++) {
            int idx = (hash + i) % capacity;
            if (states[idx] == 0) return -1; // stop search
            if (states[idx] == 1 && keys[idx] == key) return idx;
        }
        return -1;
    }

    private void resize(int newCap) {
        int[] oldKeys = keys;
        Object[] oldValues = values;
        byte[] oldStates = states;
        int oldCap = capacity;

        capacity = newCap;
        keys = new int[capacity];
        values = new Object[capacity];
        states = new byte[capacity];
        size = 0;

        for (int i = 0; i < oldCap; i++) {
            if (oldStates[i] == 1) {
                internalInsert(oldKeys[i], oldValues[i]);
            }
        }
    }

    private void internalInsert(int key, Object value) {
        int hash = (key & 0x7fffffff) % capacity;
        for (int i = 0; i < capacity; i++) {
            int idx = (hash + i) % capacity;
            if (states[idx] != 1) {
                keys[idx] = key;
                values[idx] = value;
                states[idx] = 1;
                size++;
                return;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int[] keys() {
        int[] result = new int[size];
        int j = 0;
        for (int i = 0; i < capacity; i++) {
            if (states[i] == 1) {
                result[j++] = keys[i];
            }
        }
        return result;
    }
}
