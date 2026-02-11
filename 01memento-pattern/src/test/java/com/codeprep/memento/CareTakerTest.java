package com.codeprep.memento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CareTaker Tests")
class CareTakerTest {

    private CareTaker careTaker;
    private TextEditor textEditor;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        careTaker = new CareTaker();
        textEditor = new TextEditor();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("Should save editor state")
    void testSaveEditorState() {
        textEditor.write("First state");

        assertDoesNotThrow(() -> careTaker.save(textEditor));
    }

    @Test
    @DisplayName("Should save multiple editor states")
    void testSaveMultipleStates() {
        textEditor.write("State 1");
        careTaker.save(textEditor);

        textEditor.write("State 2");
        careTaker.save(textEditor);

        textEditor.write("State 3");
        careTaker.save(textEditor);

        assertEquals("State 3", textEditor.getContent());
    }

    @Test
    @DisplayName("Should undo to previous state")
    void testUndoToPreviousState() {
        textEditor.write("State 1");
        careTaker.save(textEditor);

        textEditor.write("State 2");
        careTaker.save(textEditor);

        careTaker.undo(textEditor);

        assertEquals("State 1", textEditor.getContent());
        assertTrue(outputStream.toString().contains("Restored to previous state"));
    }

    @Test
    @DisplayName("Should undo multiple times")
    void testMultipleUndos() {
        textEditor.write("State 1");
        careTaker.save(textEditor);

        textEditor.write("State 2");
        careTaker.save(textEditor);

        textEditor.write("State 3");
        careTaker.save(textEditor);

        careTaker.undo(textEditor); // Back to State 2
        assertEquals("State 2", textEditor.getContent());

        careTaker.undo(textEditor); // Back to State 1
        assertEquals("State 1", textEditor.getContent());
    }

    @Test
    @DisplayName("Should handle undo when no history exists")
    void testUndoWithoutHistory() {
        careTaker.undo(textEditor);

        assertTrue(outputStream.toString().contains("No saved state to restore"));
    }

    @Test
    @DisplayName("Should handle undo when only one state exists")
    void testUndoWithSingleState() {
        textEditor.write("Only state");
        careTaker.save(textEditor);

        careTaker.undo(textEditor);

        assertTrue(outputStream.toString().contains("No saved state to restore"));
    }

    @Test
    @DisplayName("Should maintain correct state after multiple save and undo operations")
    void testComplexSaveAndUndoScenario() {
        // Save State 1
        textEditor.write("State 1");
        careTaker.save(textEditor);

        // Save State 2
        textEditor.write("State 2");
        careTaker.save(textEditor);

        // Save State 3
        textEditor.write("State 3");
        careTaker.save(textEditor);

        // Undo to State 2
        careTaker.undo(textEditor);
        assertEquals("State 2", textEditor.getContent());

        // Undo to State 1
        careTaker.undo(textEditor);
        assertEquals("State 1", textEditor.getContent());

        // Try to undo beyond available history
        careTaker.undo(textEditor);
        assertTrue(outputStream.toString().contains("No saved state to restore"));
    }

    @Test
    @DisplayName("Should save empty content")
    void testSaveEmptyContent() {
        textEditor.write("");
        careTaker.save(textEditor);

        textEditor.write("Some content");
        careTaker.save(textEditor);

        careTaker.undo(textEditor);
        assertEquals("", textEditor.getContent());
    }

    @Test
    @DisplayName("Should save null content")
    void testSaveNullContent() {
        textEditor.write(null);
        careTaker.save(textEditor);

        textEditor.write("Some content");
        careTaker.save(textEditor);

        careTaker.undo(textEditor);
        assertNull(textEditor.getContent());
    }

    @Test
    @DisplayName("Should handle large number of states")
    void testLargeNumberOfStates() {
        for (int i = 1; i <= 100; i++) {
            textEditor.write("State " + i);
            careTaker.save(textEditor);
        }

        assertEquals("State 100", textEditor.getContent());

        // Undo 10 times
        for (int i = 0; i < 10; i++) {
            careTaker.undo(textEditor);
        }

        assertEquals("State 90", textEditor.getContent());
    }

    @Test
    @DisplayName("Should work with different text editor instances")
    void testWithDifferentEditorInstances() {
        TextEditor editor1 = new TextEditor();
        TextEditor editor2 = new TextEditor();

        editor1.write("Editor 1 content");
        careTaker.save(editor1);

        editor2.write("Editor 2 content");

        // Note: CareTaker in current implementation manages history for any editor
        // This test verifies the behavior
        assertEquals("Editor 1 content", editor1.getContent());
        assertEquals("Editor 2 content", editor2.getContent());
    }

    @Test
    @DisplayName("Should preserve special characters in history")
    void testSpecialCharactersInHistory() {
        textEditor.write("Special: !@#$%");
        careTaker.save(textEditor);

        textEditor.write("Unicode: 你好");
        careTaker.save(textEditor);

        careTaker.undo(textEditor);
        assertEquals("Special: !@#$%", textEditor.getContent());
    }

    @Test
    @DisplayName("Should handle rapid save operations")
    void testRapidSaveOperations() {
        for (int i = 0; i < 50; i++) {
            textEditor.write("Content " + i);
            careTaker.save(textEditor);
        }

        assertEquals("Content 49", textEditor.getContent());

        careTaker.undo(textEditor);
        assertEquals("Content 48", textEditor.getContent());
    }
}
