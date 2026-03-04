package com.codeprep;

import com.codeprep.service.IOrderService;
import com.codeprep.service.IPaymentService;
import com.codeprep.service.IUserService;
import com.codeprep.service.impl.OrderService;
import com.codeprep.service.impl.PaymentService;
import com.codeprep.service.impl.UserService;

public class APIGateway {

    private final IOrderService orderService;
    private final IUserService userService;
    private final IPaymentService paymentService;

    // Default constructor for convenience — uses concrete implementations
    public APIGateway() {
        this(new OrderService(), new UserService(), new PaymentService());
    }

    // Constructor injection — preferred for testing and extensibility
    public APIGateway(IOrderService orderService, IUserService userService, IPaymentService paymentService) {
        this.orderService = orderService;
        this.userService = userService;
        this.paymentService = paymentService;
    }

    public void placeOrder(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException("userName must not be null or empty");
        }

        System.out.println(userService.getUserDetails(userName));

        boolean paymentSuccess = paymentService.processPayment(userName);
        if (!paymentSuccess) {
            System.out.println("Payment failed for user: " + userName + ". Order will not be placed.");
            return;
        }

        orderService.createOrder(userName);
        System.out.println("Order placed successfully for user: " + userName);
    }
}
