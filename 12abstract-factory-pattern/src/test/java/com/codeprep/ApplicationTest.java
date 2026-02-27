package com.codeprep;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @Test
    void testWindowsFactoryCreatesWindowsButton() {
        UIFactory factory = new WindowsFactory();
        Button button = factory.createButton();
        assertInstanceOf(WindowsButton.class, button);
    }

    @Test
    void testWindowsFactoryCreatesWindowsScrollBar() {
        UIFactory factory = new WindowsFactory();
        ScrollBar scrollBar = factory.createScrollBar();
        assertInstanceOf(WindowsScrollBar.class, scrollBar);
    }

    @Test
    void testMacFactoryCreatesMacButton() {
        UIFactory factory = new MacUIFactory();
        Button button = factory.createButton();
        assertInstanceOf(MacOSButton.class, button);
    }

    @Test
    void testMacFactoryCreatesMacScrollBar() {
        UIFactory factory = new MacUIFactory();
        ScrollBar scrollBar = factory.createScrollBar();
        assertInstanceOf(MacOSScrollBar.class, scrollBar);
    }
}
