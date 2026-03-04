package com.codeprep;

import com.codeprep.service.IOrderService;
import com.codeprep.service.IPaymentService;
import com.codeprep.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class APIGatewayTest {

    @Mock
    private IOrderService orderService;

    @Mock
    private IUserService userService;

    @Mock
    private IPaymentService paymentService;

    private APIGateway apiGateway;

    @BeforeEach
    void setUp() {
        apiGateway = new APIGateway(orderService, userService, paymentService);
    }

    @Test
    void placeOrder_successfulFlow_callsAllSubsystems() {
        when(userService.getUserDetails("alice")).thenReturn("User details for username: alice");
        when(paymentService.processPayment("alice")).thenReturn(true);

        apiGateway.placeOrder("alice");

        verify(userService).getUserDetails("alice");
        verify(paymentService).processPayment("alice");
        verify(orderService).createOrder("alice");
    }

    @Test
    void placeOrder_paymentFails_orderIsNotCreated() {
        when(userService.getUserDetails("alice")).thenReturn("User details for username: alice");
        when(paymentService.processPayment("alice")).thenReturn(false);

        apiGateway.placeOrder("alice");

        verify(orderService, never()).createOrder(anyString());
    }

    @Test
    void placeOrder_nullUserName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> apiGateway.placeOrder(null));

        verifyNoInteractions(userService, orderService, paymentService);
    }

    @Test
    void placeOrder_emptyUserName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> apiGateway.placeOrder("   "));

        verifyNoInteractions(userService, orderService, paymentService);
    }
}

