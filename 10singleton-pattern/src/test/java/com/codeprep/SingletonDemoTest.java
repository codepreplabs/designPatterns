package com.codeprep;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingletonDemoTest {

    @Test
    void testSingletonReturnsSameInstance() {
        AppSettings instance1 = AppSettings.getInstance();
        AppSettings instance2 = AppSettings.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    void testSingletonIsNotNull() {
        assertNotNull(AppSettings.getInstance());
    }
}

