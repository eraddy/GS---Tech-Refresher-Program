package org.example.dta_structures_implementation;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyHashMap<K,V>  {
    private static class Entry<K,V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final List<Entry<K,V>>[] buckets;

    public MyHashMap(int capacity){
        this.capacity = capacity;
        this.buckets = new List[capacity];
        for(int i=0;i<capacity;i++){
            buckets[i] = new LinkedList<>();
        }
    }

    private int getIndex(K key){
        return Math.abs(key.hashCode() % capacity);
    }

    public void put(K key,V value){
        int index = getIndex(key);
        List<Entry<K,V>> bucket = buckets[index];

        for(Entry<K,V> entry : bucket) {
            if(entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        bucket.add(new Entry<>(key,value));
    }

    public V get(K key){
        int index = getIndex(key);
        List<Entry<K,V>> bucket = buckets[index];

        for(Entry<K,V> entry : bucket) {
            if(entry.key.equals(key))
                return entry.value;
        }
        return null;
    }

    public boolean contains(K key){
        return get(key) != null;
    }

    public void remove(K key){
        int index = getIndex(key);
        List<Entry<K,V>> bucket = buckets[index];
        bucket.removeIf(e -> e.key.equals(key));
    }

    static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>(10);

        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        System.out.println(map.get("B"));
        map.put("B", 20);
        System.out.println(map.get("B"));

        map.remove("A");
        System.out.println(map.contains("A"));
    }

}
