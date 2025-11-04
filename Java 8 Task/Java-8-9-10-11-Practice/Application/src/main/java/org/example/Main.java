package org.example;

import java.time.LocalDate;
import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
            var serviceLoader = ServiceLoader.load(Service.class);
            var service = serviceLoader.findFirst()
                    .orElseThrow(() -> new IllegalStateException("No Service implementation found"));

            System.out.println("Loaded Service Implementation: " + service.getClass().getName());

            var bankLoader = ServiceLoader.load(Bank.class);

            // Check if any banks are found
            if (!bankLoader.iterator().hasNext()) {
                System.out.println("No Bank implementations found!");
                return;
            }

            var user1 = new User("Aditya", "Shukla", LocalDate.of(2000, 5, 10));
            var user2 = new User("Garvita", "Patel", LocalDate.of(2004, 3, 15));
            var user3 = new User("Rohan", "Mehta", LocalDate.of(1995, 12, 1));

            bankLoader.forEach(bank -> {
                System.out.println("\n Using bank: " + bank.getClass().getSimpleName());

                // Create cards for each user
                var card1 = bank.createBankCard(user1, BankCardType.CREDIT);
                var card2 = bank.createBankCard(user2, BankCardType.DEBIT);
                var card3 = bank.createBankCard(user3, BankCardType.CREDIT);

                // Subscribe them
                service.subscribe(card1);
                service.subscribe(card2);
                service.subscribe(card3);

                // Display data
                System.out.println("Created Cards:");
                System.out.println(" - " + card1);
                System.out.println(" - " + card2);
                System.out.println(" - " + card3);

                System.out.println("\nAll Subscriptions:");
                service.getAllSubscriptionsByCondition(s -> true)
                        .forEach(System.out::println);

                System.out.println("\n All Users:");
                service.getAllUsers().forEach(System.out::println);

                System.out.printf("\nAverage User Age: %.2f years%n", service.getAverageUsersAge());
                System.out.println("Is Aditya Payable User? " + Service.isPayableUser(user1));
                System.out.println("Is Garvita Payable User? " + Service.isPayableUser(user2));
                System.out.println("------");
            });
    }
}
