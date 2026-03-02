package com.codeprep;

public interface EmailNotificationService {
    void send(String to, String subject, String message);
}
