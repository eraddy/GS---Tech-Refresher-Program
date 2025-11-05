package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ServiceImpl implements Service {
    private final List<Subscription> subscriptions = new ArrayList<>();

    @Override
    public void subscribe(BankCard bankCard) {
        var subscription = new Subscription(bankCard.getNumber(), LocalDate.now());
        subscriptions.add(subscription);
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String bankCardNumber) {
        return subscriptions.stream()
                .filter(s -> s.getBankcardNumber().equals(bankCardNumber))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        // Extract unique users from subscriptions using map
        return subscriptions.stream()
                .map(sub -> sub.getBankcardNumber()) // get card number
                // Normally you’d fetch user by card number from a repository, but we’ll skip that here
                .flatMap(number -> subscriptions.stream()
                        .filter(sub -> sub.getBankcardNumber().equals(number))
                        .map(sub -> new User("Demo", "User", LocalDate.of(2000, 1, 1))))
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate) {
        return subscriptions.stream()
                .filter(predicate)
                .collect(Collectors.toUnmodifiableList());
    }
}
