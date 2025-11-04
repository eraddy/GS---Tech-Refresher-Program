package org.example;

import java.util.ArrayList;
import java.util.List;

public class NewsAgency implements Subject{

    List<Observer> observers = new ArrayList<>();

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(String article) {
        for(Observer observer : observers)
            observer.update(article);
    }

    public void publishArticle(String article)
    {
        System.out.println("NewsAgency : New article published -> " + article);
        notifyObserver(article);
    }
}
