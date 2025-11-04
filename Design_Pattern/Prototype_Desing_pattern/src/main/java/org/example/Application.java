package org.example;

public class Application {
    static void main(String[] args) {
        ShapeRegistry registry = new ShapeRegistry();

        // Register prototypes
        registry.addPrototype("Big Red Circle", new Circle(10, "Red"));
        registry.addPrototype("Small Blue Rectangle", new Rectangle(4, 6, "Blue"));

        // Clone shapes from registry
        Shape clonedCircle = registry.getClone("Big Red Circle");
        Shape clonedRectangle = registry.getClone("Small Blue Rectangle");

        // Use them
        clonedCircle.draw();
        clonedRectangle.draw();

        // Verify that they are separate objects
        System.out.println(clonedCircle);
        System.out.println(clonedRectangle);
        System.out.println("Are they same object? " + (clonedCircle == registry.getClone("Big Red Circle")));
    }
}
