package org.example;


public class Application {
    public static void main(String[] args) {
        PaymentManager paymentManager = new PaymentManager(new CreditCardPayment());
        paymentManager.makePayment();
        PaymentManager paymentManager2 = new PaymentManager(new UPIPayment());
        paymentManager2.makePayment();
    }
}
