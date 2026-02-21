package com.codeprep;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Command Pattern Integration Tests")
class CommandPatternIntegrationTest {

    @Test
    @DisplayName("Should demonstrate full command pattern workflow")
    void testCompleteWorkflow() {
        TextEditor textEditor = new TextEditor();
        textEditor.addText("Hello World");

        Command boldCommand = new BoldCommand(textEditor);
        Command italicCommand = new ItalicCommand(textEditor);

        Button boldButton = new Button(boldCommand);
        Button italicButton = new Button(italicCommand);

        boldButton.onClick();
        assertEquals("<b>Hello World</b>", textEditor.getContent());

        italicButton.onClick();
        assertEquals("<i><b>Hello World</b></i>", textEditor.getContent());
    }

    @Test
    @DisplayName("Should support dynamic command reassignment at runtime")
    void testDynamicCommandReassignment() {
        TextEditor textEditor = new TextEditor();
        textEditor.addText("Hello");

        Button button = new Button(new BoldCommand(textEditor));
        button.onClick();
        assertEquals("<b>Hello</b>", textEditor.getContent());

        // Reassign the same button to italic
        button.setCommand(new ItalicCommand(textEditor));
        button.onClick();
        assertEquals("<i><b>Hello</b></i>", textEditor.getContent());
    }

    @Test
    @DisplayName("Should support clearing after formatting")
    void testClearAfterFormatting() {
        TextEditor textEditor = new TextEditor();
        textEditor.addText("Hello");

        Button boldButton = new Button(new BoldCommand(textEditor));
        Button clearButton = new Button(new ClearCommand(textEditor));

        boldButton.onClick();
        assertEquals("<b>Hello</b>", textEditor.getContent());

        clearButton.onClick();
        assertEquals("", textEditor.getContent());
    }

    @Test
    @DisplayName("Should allow reuse of commands across multiple buttons")
    void testCommandReuseAcrossButtons() {
        TextEditor textEditor = new TextEditor();
        textEditor.addText("Hello");

        Command italicCommand = new ItalicCommand(textEditor);
        Button button1 = new Button(italicCommand);
        Button button2 = new Button(italicCommand);

        button1.onClick();
        assertEquals("<i>Hello</i>", textEditor.getContent());

        button2.onClick();
        assertEquals("<i><i>Hello</i></i>", textEditor.getContent());
    }

    @Test
    @DisplayName("Should produce correct console output during full workflow")
    void testConsoleOutputDuringWorkflow() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));

        try {
            TextEditor textEditor = new TextEditor();
            textEditor.addText("Test");

            Button boldButton = new Button(new BoldCommand(textEditor));
            Button italicButton = new Button(new ItalicCommand(textEditor));
            Button clearButton = new Button(new ClearCommand(textEditor));

            boldButton.onClick();
            italicButton.onClick();
            clearButton.onClick();

            String output = out.toString();
            assertTrue(output.contains("Making text bold"));
            assertTrue(output.contains("Making text italic"));
            assertTrue(output.contains("Clearing text"));
        } finally {
            System.setOut(originalOut);
        }
    }
}

