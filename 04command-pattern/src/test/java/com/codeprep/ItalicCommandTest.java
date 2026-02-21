package com.codeprep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ItalicCommand Tests")
class ItalicCommandTest {

    private TextEditor textEditor;
    private ItalicCommand italicCommand;

    @BeforeEach
    void setUp() {
        textEditor = new TextEditor();
        italicCommand = new ItalicCommand(textEditor);
    }

    @Test
    @DisplayName("Should implement Command interface")
    void testImplementsCommandInterface() {
        assertInstanceOf(Command.class, italicCommand);
    }

    @Test
    @DisplayName("execute() should make text italic")
    void testExecuteMakesTextItalic() {
        textEditor.addText("Hello");
        italicCommand.execute();
        assertEquals("<i>Hello</i>", textEditor.getContent());
    }

    @Test
    @DisplayName("execute() on empty content should wrap empty string")
    void testExecuteOnEmptyContent() {
        italicCommand.execute();
        assertEquals("<i></i>", textEditor.getContent());
    }

    @Test
    @DisplayName("Multiple executes should wrap repeatedly")
    void testMultipleExecutes() {
        textEditor.addText("Hi");
        italicCommand.execute();
        italicCommand.execute();
        assertEquals("<i><i>Hi</i></i>", textEditor.getContent());
    }

    @Test
    @DisplayName("execute() should print to console")
    void testExecutePrintsToConsole() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        try {
            italicCommand.execute();
            assertTrue(out.toString().contains("Making text italic"));
        } finally {
            System.setOut(System.out);
        }
    }
}

