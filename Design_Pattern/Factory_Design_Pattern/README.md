# 🏭 Notification Factory Pattern

This project demonstrates the **Factory Design Pattern** in Java by dynamically creating different types of notification services — **Email**, **SMS**, and **Push Notifications** — based on the input category.

---

## 📘 Project Overview

The **Notification Factory** encapsulates object creation logic and provides a clean interface to get notification objects without exposing the creation logic to the client.

It includes:
- Interface: `Notification`
- Concrete classes: `EmailNotification`, `SMSNotification`, `PushNotification`
- Factory class: `NotificationFactory`
- Enum: `NotificationType`
- Custom exception: `InvalidNotificationType`
- Application runner: `Application`
- Unit tests: `NotificationFactoryTest`

---

## 🧩 Design Pattern Used — Factory Pattern

**Intent:**  
Define an interface for creating an object but let subclasses decide which class to instantiate. The Factory Method lets a class defer instantiation to subclasses.

**Key Benefit:**  
It removes the need for client code to be tightly coupled with specific class implementations.

---

## ⚙️ Class Structure

org.example
├── api
│ └── Notification.java
│
├── application
│ └── Application.java
│
├── entity
│ └── NotificationType.java
│
├── exception
│ └── InvalidNotificationType.java
│
├── factory
│ ├── NotificationFactory.java
│ └── NotificationFactoryTest.java
│
└── notification
├── EmailNotification.java
├── SMSNotification.java
└── PushNotification.java


---

## 🧠 How It Works

1. **Client (Application)** calls `NotificationFactory.getNotification("email")`.
2. The **Factory** looks up the type using `NotificationType.getType()`.
3. It returns the correct notification instance:
    - `"email"` → `EmailNotification`
    - `"sms"` → `SMSNotification`
    - `"push"` → `PushNotification`
4. If an invalid or null type is provided, a proper exception is thrown:
    - `InvalidNotificationType` for invalid input
    - `IllegalArgumentException` for null input

---

## 🧪 Unit Tests (JUnit 5)

The following scenarios are tested:
- ✅ Correct notification instances for `"email"`, `"sms"`, and `"push"`
- ✅ Case-insensitive type handling
- ✅ Exception handling for invalid and null inputs
- ✅ Validation of `NotificationType` enum mapping

Run tests using:
```bash
  mvn test
