package com.codeprep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BoldCommand Tests")
class BoldCommandTest {

    private TextEditor textEditor;
    private BoldCommand boldCommand;

    @BeforeEach
    void setUp() {
        textEditor = new TextEditor();
        boldCommand = new BoldCommand(textEditor);
    }

    @Test
    @DisplayName("Should implement Command interface")
    void testImplementsCommandInterface() {
        assertInstanceOf(Command.class, boldCommand);
    }

    @Test
    @DisplayName("execute() should make text bold")
    void testExecuteMakesTextBold() {
        textEditor.addText("Hello");
        boldCommand.execute();
        assertEquals("<b>Hello</b>", textEditor.getContent());
    }

    @Test
    @DisplayName("execute() on empty content should wrap empty string")
    void testExecuteOnEmptyContent() {
        boldCommand.execute();
        assertEquals("<b></b>", textEditor.getContent());
    }

    @Test
    @DisplayName("Multiple executes should wrap repeatedly")
    void testMultipleExecutes() {
        textEditor.addText("Hi");
        boldCommand.execute();
        boldCommand.execute();
        assertEquals("<b><b>Hi</b></b>", textEditor.getContent());
    }

    @Test
    @DisplayName("execute() should print to console")
    void testExecutePrintsToConsole() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        try {
            boldCommand.execute();
            assertTrue(out.toString().contains("Making text bold"));
        } finally {
            System.setOut(System.out);
        }
    }
}

