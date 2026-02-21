package com.codeprep;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DataParser (Template Method) Tests")
class DataParserTest {

    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        // Redirect so that any System.out.println inside anonymous subclasses never fails
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    // -----------------------------------------------------------------------
    // Helper: capture stdout produced by a Runnable
    // -----------------------------------------------------------------------
    private String captureOutput(Runnable action) {
        // Restore original first so we can set a fresh capture
        System.setOut(originalOut);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        try {
            action.run();
        } finally {
            System.setOut(originalOut);
        }
        return baos.toString().trim();
    }

    // -----------------------------------------------------------------------
    // Template method execution order
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("parse() should call open, doParse and close in order")
    void testParseCallsStepsInOrder() {
        // Concrete spy that records the call sequence
        DataParser spy = new DataParser() {
            final StringBuilder log = new StringBuilder();

            @Override
            protected void open() {
                log.append("open|");
            }

            @Override
            protected void doParse() {
                log.append("doParse|");
            }

            @Override
            protected void close() {
                log.append("close|");
            }

            @Override
            public String toString() {
                return log.toString();
            }
        };

        spy.parse();

        assertEquals("open|doParse|close|", spy.toString());
    }

    @Test
    @DisplayName("parse() should always call doParse even if open/close are overridden")
    void testDoParseIsAlwaysCalled() {
        boolean[] called = {false};

        DataParser parser = new DataParser() {
            @Override
            protected void doParse() {
                called[0] = true;
            }
        };

        parser.parse();

        assertTrue(called[0], "doParse() must be called by the template method");
    }

    @Test
    @DisplayName("parse() is final — subclasses cannot override it")
    void testParseMethodIsFinal() throws NoSuchMethodException {
        var method = DataParser.class.getDeclaredMethod("parse");
        assertTrue(java.lang.reflect.Modifier.isFinal(method.getModifiers()),
                "parse() must be declared final");
    }

    // -----------------------------------------------------------------------
    // Default hook behaviour
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("Default open() prints 'Opening file...'")
    void testDefaultOpenOutput() {
        DataParser parser = new DataParser() {
            @Override
            protected void doParse() { /* no-op */ }
        };

        String output = captureOutput(parser::parse);

        assertTrue(output.contains("Opening file..."),
                "Default open() should print 'Opening file...'");
    }

    @Test
    @DisplayName("Default close() prints 'Closing file...'")
    void testDefaultCloseOutput() {
        DataParser parser = new DataParser() {
            @Override
            protected void doParse() { /* no-op */ }
        };

        String output = captureOutput(parser::parse);

        assertTrue(output.contains("Closing file..."),
                "Default close() should print 'Closing file...'");
    }

    // -----------------------------------------------------------------------
    // Hook override behaviour
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("Subclass can override open() hook without affecting other steps")
    void testHookOverrideDoesNotBreakOtherSteps() {
        boolean[] closeCalled = {false};

        DataParser parser = new DataParser() {
            @Override
            protected void open() {
                System.out.println("Custom open");
            }

            @Override
            protected void doParse() { /* no-op */ }

            @Override
            protected void close() {
                closeCalled[0] = true;
            }
        };

        parser.parse();

        assertTrue(closeCalled[0], "close() must still be called after a custom open()");
    }
}


