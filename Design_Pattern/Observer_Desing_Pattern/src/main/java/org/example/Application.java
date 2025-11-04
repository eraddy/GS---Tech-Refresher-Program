package org.example;

public class Application {
    public static void main(String[] args) {
        NewsAgency newAgency = new NewsAgency();

        Subscriber john = new Subscriber("john");
        Subscriber emma = new Subscriber("emma");

        newAgency.register(john);
        newAgency.register(emma);

        newAgency.publishArticle("Observer pattern explained");
        newAgency.publishArticle("New java 25 release");

        newAgency.unregister(john);
        newAgency.publishArticle("Observer pattern explained with example");
    }
}
