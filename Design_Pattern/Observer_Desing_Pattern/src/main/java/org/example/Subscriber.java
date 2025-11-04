package org.example;

public class Subscriber implements Observer{

    private final String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String article) {
        System.out.println(name + " received update " + article);
    }

    public String getName()
    {
        return name;
    }
}
