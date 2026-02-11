package com.codeprep.memento;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("EditorMemento Tests")
class EditorMementoTest {

    @Test
    @DisplayName("Should create memento with content")
    void testCreateMementoWithContent() {
        String content = "Test content";
        EditorMemento memento = new EditorMemento(content);

        assertNotNull(memento);
        assertEquals(content, memento.getContent());
    }

    @Test
    @DisplayName("Should create memento with null content")
    void testCreateMementoWithNullContent() {
        EditorMemento memento = new EditorMemento(null);

        assertNotNull(memento);
        assertNull(memento.getContent());
    }

    @Test
    @DisplayName("Should create memento with empty string")
    void testCreateMementoWithEmptyString() {
        EditorMemento memento = new EditorMemento("");

        assertNotNull(memento);
        assertEquals("", memento.getContent());
    }

    @Test
    @DisplayName("Should preserve content immutability")
    void testContentImmutability() {
        String content = "Original content";
        EditorMemento memento = new EditorMemento(content);

        String retrievedContent = memento.getContent();

        assertEquals(content, retrievedContent);
        // Since String is immutable in Java, this tests that memento holds the correct reference
    }

    @Test
    @DisplayName("Should create multiple mementos with different content")
    void testMultipleMementos() {
        EditorMemento memento1 = new EditorMemento("Content 1");
        EditorMemento memento2 = new EditorMemento("Content 2");
        EditorMemento memento3 = new EditorMemento("Content 3");

        assertEquals("Content 1", memento1.getContent());
        assertEquals("Content 2", memento2.getContent());
        assertEquals("Content 3", memento3.getContent());
    }

    @Test
    @DisplayName("Should store long content")
    void testStoreLongContent() {
        StringBuilder longContent = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            longContent.append("Line ").append(i).append(" ");
        }

        EditorMemento memento = new EditorMemento(longContent.toString());

        assertEquals(longContent.toString(), memento.getContent());
    }

    @Test
    @DisplayName("Should store special characters")
    void testStoreSpecialCharacters() {
        String specialContent = "Special: !@#$%^&*()_+-=[]{}|;':\",./<>?";
        EditorMemento memento = new EditorMemento(specialContent);

        assertEquals(specialContent, memento.getContent());
    }

    @Test
    @DisplayName("Should store unicode characters")
    void testStoreUnicodeCharacters() {
        String unicodeContent = "Unicode: 你好 🎉 مرحبا";
        EditorMemento memento = new EditorMemento(unicodeContent);

        assertEquals(unicodeContent, memento.getContent());
    }

    @Test
    @DisplayName("Should store multiline content")
    void testStoreMultilineContent() {
        String multilineContent = "Line 1\nLine 2\nLine 3\nLine 4";
        EditorMemento memento = new EditorMemento(multilineContent);

        assertEquals(multilineContent, memento.getContent());
    }

    @Test
    @DisplayName("Should store content with tabs and spaces")
    void testStoreContentWithTabsAndSpaces() {
        String contentWithWhitespace = "    Indented\t\twith\ttabs";
        EditorMemento memento = new EditorMemento(contentWithWhitespace);

        assertEquals(contentWithWhitespace, memento.getContent());
    }
}
