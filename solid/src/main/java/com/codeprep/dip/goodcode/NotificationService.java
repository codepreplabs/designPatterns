package com.codeprep.dip.goodcode;

/*
 * DEPENDENCY INVERSION PRINCIPLE (DIP) - CORRECT IMPLEMENTATION
 *
 * HOW THIS CLASS FOLLOWS DIP:
 *
 * 1. DEPENDS ON ABSTRACTION, NOT CONCRETE CLASSES:
 *    - NotificationService now depends on the NotificationChannel interface (abstraction)
 *    - It doesn't know or care about concrete implementations (EmailService, SMSService, etc.)
 *
 * 2. LOOSE COUPLING:
 *    - The high-level module (NotificationService) is decoupled from low-level modules
 *    - We can inject ANY implementation of NotificationChannel without changing this class
 *
 * 3. OPEN FOR EXTENSION, CLOSED FOR MODIFICATION:
 *    - Adding new notification types (Push, WhatsApp, Slack) requires NO changes here
 *    - Just create a new class implementing NotificationChannel
 *
 * 4. EASY TO TEST:
 *    - We can inject mock/stub implementations of NotificationChannel for unit testing
 *    - No need to actually send emails or SMS during tests
 *
 * 5. FLEXIBILITY:
 *    - The same NotificationService can work with different channels at runtime
 *    - Dependency is injected via constructor (Dependency Injection pattern)
 *
 * BENEFITS:
 * - Better maintainability
 * - Higher testability
 * - Greater flexibility
 * - Reduced coupling between modules
 */
public class NotificationService {

    private final NotificationChannel notificationChannel;

    public NotificationService(NotificationChannel notificationChannel) {
        this.notificationChannel = notificationChannel;
    }

    public void sendNotification(String to, String subject, String body){
        notificationChannel.sendNotification(to, subject, body);
    }
}
