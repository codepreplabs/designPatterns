package com.codeprep.memento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TextEditor Tests")
class TextEditorTest {

    private TextEditor textEditor;

    @BeforeEach
    void setUp() {
        textEditor = new TextEditor();
    }

    @Test
    @DisplayName("Should write content to editor")
    void testWriteContent() {
        String content = "Hello, World!";
        textEditor.write(content);

        assertEquals(content, textEditor.getContent());
    }

    @Test
    @DisplayName("Should overwrite existing content")
    void testOverwriteContent() {
        textEditor.write("First content");
        textEditor.write("Second content");

        assertEquals("Second content", textEditor.getContent());
    }

    @Test
    @DisplayName("Should write empty string")
    void testWriteEmptyString() {
        textEditor.write("");

        assertEquals("", textEditor.getContent());
    }

    @Test
    @DisplayName("Should write null content")
    void testWriteNullContent() {
        textEditor.write(null);

        assertNull(textEditor.getContent());
    }

    @Test
    @DisplayName("Should write long content")
    void testWriteLongContent() {
        StringBuilder longContent = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            longContent.append("This is line ").append(i).append(". ");
        }

        textEditor.write(longContent.toString());

        assertEquals(longContent.toString(), textEditor.getContent());
    }

    @Test
    @DisplayName("Should return null when no content is written")
    void testGetContentWhenEmpty() {
        assertNull(textEditor.getContent());
    }

    @Test
    @DisplayName("Should save current state as memento")
    void testSaveMemento() {
        String content = "Test content";
        textEditor.write(content);

        EditorMemento memento = textEditor.save();

        assertNotNull(memento);
        assertEquals(content, memento.getContent());
    }

    @Test
    @DisplayName("Should save memento with null content")
    void testSaveMementoWithNullContent() {
        EditorMemento memento = textEditor.save();

        assertNotNull(memento);
        assertNull(memento.getContent());
    }

    @Test
    @DisplayName("Should restore content from memento")
    void testRestoreFromMemento() {
        String originalContent = "Original content";
        textEditor.write(originalContent);
        EditorMemento memento = textEditor.save();

        textEditor.write("Modified content");
        textEditor.restore(memento);

        assertEquals(originalContent, textEditor.getContent());
    }

    @Test
    @DisplayName("Should restore null content from memento")
    void testRestoreNullContentFromMemento() {
        textEditor.write("Some content");
        EditorMemento nullMemento = new EditorMemento(null);

        textEditor.restore(nullMemento);

        assertNull(textEditor.getContent());
    }

    @Test
    @DisplayName("Should handle multiple save and restore operations")
    void testMultipleSaveAndRestore() {
        textEditor.write("Version 1");
        EditorMemento v1 = textEditor.save();

        textEditor.write("Version 2");
        EditorMemento v2 = textEditor.save();

        textEditor.write("Version 3");

        textEditor.restore(v2);
        assertEquals("Version 2", textEditor.getContent());

        textEditor.restore(v1);
        assertEquals("Version 1", textEditor.getContent());
    }

    @Test
    @DisplayName("Should write special characters")
    void testWriteSpecialCharacters() {
        String specialContent = "!@#$%^&*()_+-=[]{}|;':\",./<>?\\n\\t";
        textEditor.write(specialContent);

        assertEquals(specialContent, textEditor.getContent());
    }

    @Test
    @DisplayName("Should write unicode characters")
    void testWriteUnicodeCharacters() {
        String unicodeContent = "Hello 世界 🌍 مرحبا";
        textEditor.write(unicodeContent);

        assertEquals(unicodeContent, textEditor.getContent());
    }
}
