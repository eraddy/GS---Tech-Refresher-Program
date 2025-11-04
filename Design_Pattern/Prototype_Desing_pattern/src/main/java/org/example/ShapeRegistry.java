package org.example;

import java.util.HashMap;
import java.util.Map;

public class ShapeRegistry {
    private final Map<String, Shape> registry = new HashMap<>();

    public void addPrototype(String key, Shape shape) {
        registry.put(key, shape);
    }

    public Shape getClone(String key) {
        Shape shape = registry.get(key);
        if (shape == null) {
            throw new IllegalArgumentException("No shape registered with key: " + key);
        }
        return shape.clone();
    }
}
