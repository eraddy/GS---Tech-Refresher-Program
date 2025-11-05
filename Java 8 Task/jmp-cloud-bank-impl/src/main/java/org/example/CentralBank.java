package org.example;

public class CentralBank implements Bank{
    @Override
    public BankCard createBankCard(User user, BankCardType type) {
        String cardNumber = "CB-" + System.currentTimeMillis();
        return switch (type) {
            case CREDIT -> new CreditBankCard(cardNumber, user);
            case DEBIT  -> new DebitBankCard(cardNumber, user);
        };
    }

    @Override
    public String toString() {
        return "CentralBank";
    }
}