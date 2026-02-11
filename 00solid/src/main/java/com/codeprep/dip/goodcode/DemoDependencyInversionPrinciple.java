package com.codeprep.dip.goodcode;

public class DemoDependencyInversionPrinciple {

    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService(new EmailService());
        notificationService.sendNotification("john doe", "dependency inversion principle", "testing DIP!");
    }
}
