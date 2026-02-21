package com.codeprep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Button (Invoker) Tests")
class ButtonTest {

    private TextEditor textEditor;

    @BeforeEach
    void setUp() {
        textEditor = new TextEditor();
        textEditor.addText("Hello");
    }

    @Test
    @DisplayName("onClick() should execute the assigned bold command")
    void testOnClickExecutesBoldCommand() {
        Button button = new Button(new BoldCommand(textEditor));
        button.onClick();
        assertEquals("<b>Hello</b>", textEditor.getContent());
    }

    @Test
    @DisplayName("onClick() should execute the assigned italic command")
    void testOnClickExecutesItalicCommand() {
        Button button = new Button(new ItalicCommand(textEditor));
        button.onClick();
        assertEquals("<i>Hello</i>", textEditor.getContent());
    }

    @Test
    @DisplayName("onClick() should execute the assigned clear command")
    void testOnClickExecutesClearCommand() {
        Button button = new Button(new ClearCommand(textEditor));
        button.onClick();
        assertEquals("", textEditor.getContent());
    }

    @Test
    @DisplayName("setCommand() should allow dynamic command reassignment")
    void testSetCommandAllowsReassignment() {
        Button button = new Button(new BoldCommand(textEditor));
        button.onClick();
        assertEquals("<b>Hello</b>", textEditor.getContent());

        // Reassign to italic command
        button.setCommand(new ItalicCommand(textEditor));
        button.onClick();
        assertEquals("<i><b>Hello</b></i>", textEditor.getContent());
    }

    @Test
    @DisplayName("onClick() with null command should not throw exception")
    void testOnClickWithNullCommandDoesNotThrow() {
        Button button = new Button(null);
        assertDoesNotThrow(button::onClick);
    }

    @Test
    @DisplayName("onClick() should trigger console output from the command")
    void testOnClickTriggersConsoleOutput() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        try {
            Button button = new Button(new BoldCommand(textEditor));
            button.onClick();
            assertTrue(out.toString().contains("Making text bold"));
        } finally {
            System.setOut(System.out);
        }
    }
}

