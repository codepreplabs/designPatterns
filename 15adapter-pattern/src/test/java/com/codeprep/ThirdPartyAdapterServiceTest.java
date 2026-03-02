package com.codeprep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class ThirdPartyAdapterServiceTest {

    @Mock
    private ThirdPartyEmailNotificationService adaptee;

    private ThirdPartyAdapterService adapter;

    @BeforeEach
    void setUp() {
        adapter = new ThirdPartyAdapterService(adaptee);
    }

    @Test
    void send_shouldDelegateToAdapteeWithSameArguments() {
        String to = "user@example.com";
        String subject = "Hello";
        String message = "Test body";

        adapter.send(to, subject, message);

        verify(adaptee).sendEmail(to, subject, message);
    }

    @Test
    void send_shouldNotInvokeAnyOtherMethodOnAdaptee() {
        adapter.send("a@b.com", "Subject", "Body");

        verify(adaptee).sendEmail("a@b.com", "Subject", "Body");
        verifyNoMoreInteractions(adaptee);
    }

    @Test
    void send_implementsTargetInterface() {
        // Adapter must be usable wherever the target interface is expected
        EmailNotificationService service = adapter;
        service.send("x@y.com", "Subject", "Body");

        verify(adaptee).sendEmail("x@y.com", "Subject", "Body");
    }
}


