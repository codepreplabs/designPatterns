package com.codeprep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ClearCommand Tests")
class ClearCommandTest {

    private TextEditor textEditor;
    private ClearCommand clearCommand;

    @BeforeEach
    void setUp() {
        textEditor = new TextEditor();
        clearCommand = new ClearCommand(textEditor);
    }

    @Test
    @DisplayName("Should implement Command interface")
    void testImplementsCommandInterface() {
        assertInstanceOf(Command.class, clearCommand);
    }

    @Test
    @DisplayName("execute() should clear all content")
    void testExecuteClearsContent() {
        textEditor.addText("Hello World");
        clearCommand.execute();
        assertEquals("", textEditor.getContent());
    }

    @Test
    @DisplayName("execute() on already empty editor should keep it empty")
    void testExecuteOnEmptyEditor() {
        clearCommand.execute();
        assertEquals("", textEditor.getContent());
    }

    @Test
    @DisplayName("execute() should clear formatted content too")
    void testExecuteClearsFormattedContent() {
        textEditor.addText("Hello");
        textEditor.bold();
        textEditor.italic();
        clearCommand.execute();
        assertEquals("", textEditor.getContent());
    }

    @Test
    @DisplayName("execute() should print to console")
    void testExecutePrintsToConsole() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        try {
            clearCommand.execute();
            assertTrue(out.toString().contains("Clearing text"));
        } finally {
            System.setOut(System.out);
        }
    }

    @Test
    @DisplayName("undo() should print undo message to console")
    void testUndoPrintsToConsole() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        try {
            textEditor.addText("Hello");
            clearCommand.execute();
            clearCommand.undo();
            assertTrue(out.toString().contains("Undoing clear operation"));
        } finally {
            System.setOut(System.out);
        }
    }
}

