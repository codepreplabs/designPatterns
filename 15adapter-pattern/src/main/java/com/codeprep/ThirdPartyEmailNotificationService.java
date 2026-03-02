package com.codeprep;

public class ThirdPartyEmailNotificationService {

    public void sendEmail(String toEmail, String subject, String body){

        System.out.println("Sending email using Third party");
        System.out.println("Sending email to " + toEmail);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
    }
}
