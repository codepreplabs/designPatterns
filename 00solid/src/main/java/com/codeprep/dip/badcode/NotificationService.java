package com.codeprep.dip.badcode;

/*
 * DEPENDENCY INVERSION PRINCIPLE (DIP) VIOLATION
 *
 * The Dependency Inversion Principle states:
 * 1. High-level modules should not depend on low-level modules. Both should depend on abstractions.
 * 2. Abstractions should not depend on details. Details should depend on abstractions.
 *
 * HOW THIS CLASS VIOLATES DIP:
 *
 * - NotificationService (high-level module) directly depends on concrete implementations
 *   EmailService and SMSService (low-level modules)
 * - This creates tight coupling - if we want to add a new notification type (e.g., PushNotification),
 *   we must modify this class
 * - The class cannot work with any other notification mechanism without code changes
 * - It's difficult to test in isolation since we're tied to concrete implementations
 *
 * TO FIX (Apply DIP):
 * - Create an abstraction (interface) like MessageSender or NotificationChannel
 * - Make EmailService and SMSService implement this interface
 * - Have NotificationService depend on the abstraction instead of concrete classes
 * - This allows adding new notification types without modifying NotificationService
 */
public class NotificationService {

    private final EmailService emailService;
    private final SMSService smsService;

    public NotificationService(EmailService emailService, SMSService smsService){
        this.emailService = emailService;
        this.smsService = smsService;
    }

    public void sendNotification(String to, String subject, String body, String notificationType){
        if(notificationType.equals("SMS")){
            smsService.sendSMS(to, subject, body);
        } else if (notificationType.equals("EMAIL")) {
            emailService.sendEmail(to, subject, body);
        }
    }
}
