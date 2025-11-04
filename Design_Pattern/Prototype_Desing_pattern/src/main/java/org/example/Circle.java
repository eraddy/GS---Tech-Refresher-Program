package org.example;

public class Circle implements Shape {

    private int radius;
    private String color;

    public Circle(int radius,String color)
    {
        this.color = color;
        this.radius = radius;
    }

    private Circle(Circle source)
    {
        this.radius = source.radius;
        this.color = source.color;
    }

    @Override
    public Shape clone(){
        return new Circle(this);
    }

    @Override
    public void draw()
    {
        System.out.println("Drawing a Circle [radius=" + radius + ", color=" + color + "]");
    }

    @Override
    public String toString(){
        return "Circle{radius=" + radius + ", color='" + color + "'}";
    }

}
