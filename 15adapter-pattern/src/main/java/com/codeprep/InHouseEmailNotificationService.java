package com.codeprep;

public class InHouseEmailNotificationService implements EmailNotificationService {

    public void send(String toEmail, String subject, String body){
        System.out.println("Email Notification Service");
        System.out.println("To Email: " + toEmail);
        System.out.println("Subject : " + subject);
        System.out.println("Body : " + body);
    }
}
