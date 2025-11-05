package org.example;

public class InvestmentBank implements Bank{
    @Override
    public BankCard createBankCard(User user, BankCardType type) {
        String cardNumber = "IB-" + System.currentTimeMillis();
        return switch (type) {
            case CREDIT -> new CreditBankCard(cardNumber, user);
            case DEBIT  -> new DebitBankCard(cardNumber, user);
        };
    }

    @Override
    public String toString() {
        return "InvestmentBank";
    }
}
