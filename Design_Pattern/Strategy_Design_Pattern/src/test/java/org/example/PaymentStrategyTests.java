package org.example;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class PaymentStrategyTests {
    @Test
    void testCreditCardPayment() {
        // Arrange
        PaymentStrategy strategy = mock(CreditCardPayment.class);
        PaymentManager manager = new PaymentManager(strategy);

        // Act
        manager.makePayment();

        // Assert
        verify(strategy, times(1)).pay();
    }

    @Test
    void testUPIPayment() {
        // Arrange
        PaymentStrategy strategy = mock(UPIPayment.class);
        PaymentManager manager = new PaymentManager(strategy);

        // Act
        manager.makePayment();

        // Assert
        verify(strategy, times(1)).pay();
    }

    @Test
    void testRealOutputCreditCardPayment() {
        // This test uses actual implementation, not mock
        PaymentManager manager = new PaymentManager(new CreditCardPayment());
        assertDoesNotThrow(manager::makePayment); // should not throw exception
    }

    @Test
    void testRealOutputUPIPayment() {
        PaymentManager manager = new PaymentManager(new UPIPayment());
        assertDoesNotThrow(manager::makePayment);
    }

    @Test
    void testNullStrategyThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new PaymentManager(null));
    }
}
