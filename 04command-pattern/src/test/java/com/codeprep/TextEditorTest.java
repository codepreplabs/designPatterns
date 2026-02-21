package com.codeprep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TextEditor (Receiver) Tests")
class TextEditorTest {

    private TextEditor textEditor;

    @BeforeEach
    void setUp() {
        textEditor = new TextEditor();
    }

    @Test
    @DisplayName("Should start with empty content")
    void testInitialContentIsEmpty() {
        assertEquals("", textEditor.getContent());
    }

    @Test
    @DisplayName("Should add text correctly")
    void testAddText() {
        textEditor.addText("Hello World");
        assertEquals("Hello World", textEditor.getContent());
    }

    @Test
    @DisplayName("Should append text on subsequent addText calls")
    void testAddTextAppends() {
        textEditor.addText("Hello");
        textEditor.addText(" World");
        assertEquals("Hello World", textEditor.getContent());
    }

    @Test
    @DisplayName("Should wrap content with bold tags")
    void testBold() {
        textEditor.addText("Hello");
        textEditor.bold();
        assertEquals("<b>Hello</b>", textEditor.getContent());
    }

    @Test
    @DisplayName("Should wrap content with italic tags")
    void testItalic() {
        textEditor.addText("Hello");
        textEditor.italic();
        assertEquals("<i>Hello</i>", textEditor.getContent());
    }

    @Test
    @DisplayName("Should clear all content")
    void testClear() {
        textEditor.addText("Hello World");
        textEditor.clear();
        assertEquals("", textEditor.getContent());
    }

    @Test
    @DisplayName("Should support chained formatting - bold then italic")
    void testBoldThenItalic() {
        textEditor.addText("Hello");
        textEditor.bold();
        textEditor.italic();
        assertEquals("<i><b>Hello</b></i>", textEditor.getContent());
    }

    @Test
    @DisplayName("bold() should print to console")
    void testBoldPrintsToConsole() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        try {
            textEditor.bold();
            assertTrue(out.toString().contains("Making text bold"));
        } finally {
            System.setOut(System.out);
        }
    }

    @Test
    @DisplayName("italic() should print to console")
    void testItalicPrintsToConsole() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        try {
            textEditor.italic();
            assertTrue(out.toString().contains("Making text italic"));
        } finally {
            System.setOut(System.out);
        }
    }

    @Test
    @DisplayName("clear() should print to console")
    void testClearPrintsToConsole() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        try {
            textEditor.clear();
            assertTrue(out.toString().contains("Clearing text"));
        } finally {
            System.setOut(System.out);
        }
    }
}

