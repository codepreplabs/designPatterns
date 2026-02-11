package com.codeprep.dip.goodcode;

public class SMSService implements NotificationChannel {

    @Override
    public void sendNotification(String to, String from, String msg){
        System.out.println("Sending SMS to " + to);
    }
}
