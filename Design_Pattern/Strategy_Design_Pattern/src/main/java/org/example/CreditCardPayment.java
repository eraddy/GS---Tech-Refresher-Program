package org.example;

public class CreditCardPayment implements PaymentStrategy {

    @Override
    public void pay() {
        System.out.println("Payment made through credit card");
    }
}
