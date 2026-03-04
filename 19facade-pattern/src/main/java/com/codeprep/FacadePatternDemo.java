package com.codeprep;

public class FacadePatternDemo {

    public static void main(String[] args) {
        APIGateway apiGateway = new APIGateway();
        apiGateway.placeOrder("testUser");
    }
}
