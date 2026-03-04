package com.codeprep.service.impl;

import com.codeprep.service.IOrderService;

public class OrderService implements IOrderService {

    @Override
    public void createOrder(String userName) {
        System.out.println("Order created for user: " + userName);
    }
}
