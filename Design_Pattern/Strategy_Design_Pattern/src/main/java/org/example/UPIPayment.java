package org.example;

public class UPIPayment implements PaymentStrategy {
    @Override
    public void pay() {
        System.out.println("Payment made through UPI");
    }
}
