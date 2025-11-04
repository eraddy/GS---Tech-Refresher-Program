package org.example;

public class PaymentManager {
    private final PaymentStrategy paymentStrategy;
    public PaymentManager(PaymentStrategy paymentStrategy)
    {
        if(paymentStrategy == null)
            throw new IllegalArgumentException("Null payment strategy is not valid");
        this.paymentStrategy = paymentStrategy;
    }

    public void makePayment()
    {
        paymentStrategy.pay();
    }
}
