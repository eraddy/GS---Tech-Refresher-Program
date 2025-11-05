package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Service {
    void subscribe(BankCard bankCard);
    Optional<Subscription> getSubscriptionByBankCardNumber(String number);
    List<User> getAllUsers();

    // ✅ Default method - calculate average user age
    default double getAverageUsersAge() {
        var now = LocalDate.now();
        return getAllUsers().stream()
                .mapToLong(user -> ChronoUnit.YEARS.between(user.getBirthDay(), now))
                .average()
                .orElse(0.0);
    }

    // ✅ Static method - check if user is over 18
    static boolean isPayableUser(User user) {
        return ChronoUnit.YEARS.between(user.getBirthDay(), LocalDate.now()) >= 18;
    }

    // ✅ Method for filtering subscriptions
    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate);
}
