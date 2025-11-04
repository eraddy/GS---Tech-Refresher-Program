package org.example;

public class RetailBank implements Bank {

    @Override
    public BankCard createBankCard(User user, BankCardType type) {
        String cardNumber = "RB-" + System.currentTimeMillis();
        return switch (type) {
            case CREDIT -> new CreditBankCard(cardNumber, user);
            case DEBIT  -> new DebitBankCard(cardNumber, user);
        };
    }

    @Override
    public String toString() {
        return "RetailBank";
    }
}
