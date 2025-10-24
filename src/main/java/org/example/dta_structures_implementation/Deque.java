package org.example.dta_structures_implementation;

interface Stack<T>
{
    boolean push(T t);
    T pop();
}

interface Queue<T>
{
    boolean offer(T t);
    T poll();
}

public class Deque<T> implements Stack<T>, Queue<T>{
    private T[] arr;
    private int front, rear, size, capacity;

    @SuppressWarnings("unchecked")
    public Deque(int n) {
        this.arr = (T[]) new Object[n];
        this.capacity = n;
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    // Add at the rear (Queue behavior)
    public boolean offer(T t) {
        if (size == capacity) return false;
        rear = (rear + 1) % capacity;
        arr[rear] = t;
        size++;
        return true;
    }

    // Remove from the front (Queue behavior)
    public T poll() {
        if (size == 0) return null;
        T val = arr[front];
        front = (front + 1) % capacity;
        size--;
        return val;
    }

    // Push to the front (Stack-like behavior)
    public boolean push(T t) {
        if (size == capacity) return false;
        front = (front - 1 + capacity) % capacity;
        arr[front] = t;
        size++;
        return true;
    }

    // Pop from the front (Stack-like behavior)
    public T pop() {
        if (size == 0) return null;
        T val = arr[front];
        front = (front + 1) % capacity;
        size--;
        return val;
    }

    public void display() {
        System.out.print("Deque: ");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[(front + i) % capacity] + " ");
        }
        System.out.println();
    }

    static void main() {
        Deque<String> deque = new Deque<>(5);

        deque.offer("A");
        deque.offer("B");
        deque.push("C");
        deque.display();

        System.out.println("Popped: " + deque.pop());
        deque.display();
    }
}
