package com.codeprep;

public class ThirdPartyAdapterService implements EmailNotificationService {

    private final ThirdPartyEmailNotificationService adaptee;

    public ThirdPartyAdapterService(ThirdPartyEmailNotificationService adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void send(String to, String subject, String message) {
        adaptee.sendEmail(to, subject, message);
    }
}
