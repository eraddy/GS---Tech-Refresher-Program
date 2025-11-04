import org.example.Circle;
import org.example.Rectangle;
import org.example.Shape;
import org.example.ShapeRegistry;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShapePrototypeTests {
    @Test
    void testCloningCreatesIndependentObjects() {
        ShapeRegistry registry = new ShapeRegistry();
        registry.addPrototype("Circle", new Circle(5, "Green"));
        registry.addPrototype("Rectangle", new Rectangle(3, 7, "Yellow"));

        Shape circle1 = registry.getClone("Circle");
        Shape circle2 = registry.getClone("Circle");

        assertNotSame(circle1, circle2, "Each clone should be a different instance");
        assertInstanceOf(Circle.class, circle1);
        assertInstanceOf(Circle.class, circle2);
    }

    @Test
    void testInvalidKeyThrowsException() {
        ShapeRegistry registry = new ShapeRegistry();
        assertThrows(IllegalArgumentException.class, () -> registry.getClone("Nonexistent"));
    }
}
