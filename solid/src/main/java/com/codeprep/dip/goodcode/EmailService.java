package com.codeprep.dip.goodcode;

public class EmailService implements NotificationChannel {

    @Override
    public void sendNotification(String to, String subject, String body){
        System.out.println("Sending email to " + to);
    }
}
