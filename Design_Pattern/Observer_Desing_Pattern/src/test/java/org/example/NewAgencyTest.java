package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.mockito.Mockito.*;

class NewAgencyTest {

    private NewsAgency agency;
    private Observer observer1;
    private Observer observer2;

    @BeforeEach
    void setUp() {
        agency = new NewsAgency();
        observer1 = Mockito.mock(Observer.class);
        observer2 = Mockito.mock(Observer.class);
    }

    @Test
    void testNotifyObservers() {
        agency.register(observer1);
        agency.register(observer2);

        agency.publishArticle("New Tech Article!");

        verify(observer1, times(1)).update("New Tech Article!");
        verify(observer2, times(1)).update("New Tech Article!");
    }

    @Test
    void testUnregisterStopsNotifications() {
        agency.register(observer1);
        agency.register(observer2);
        agency.unregister(observer1);

        agency.publishArticle("Exclusive Interview!");

        verify(observer1, never()).update(anyString());
        verify(observer2, times(1)).update("Exclusive Interview!");
    }

}