package com.codeprep;

public class AdapterDemo {

    static void main() {
        System.out.println("=== In-House Email Service ===");
        EmailNotificationService inHouse = new InHouseEmailNotificationService();
        inHouse.send("alice@example.com", "Welcome", "Hello from in-house service!");

        System.out.println();

        System.out.println("=== Third-Party Email Service (via Adapter) ===");
        EmailNotificationService thirdParty = new ThirdPartyAdapterService(
                new ThirdPartyEmailNotificationService()
        );
        thirdParty.send("bob@example.com", "Welcome", "Hello from third-party service!");
    }
}
