package com.codeprep;

import com.codeprep.memento.CareTaker;
import com.codeprep.memento.TextEditor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MementoApp Integration Tests")
class MementoAppTest {

    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("Should run main method without errors")
    void testMainMethod() {
        assertDoesNotThrow(() -> MementoApp.main(new String[]{}));
    }

    @Test
    @DisplayName("Should produce expected output from main method")
    void testMainMethodOutput() {
        MementoApp.main(new String[]{});

        String output = outputStream.toString();
        assertTrue(output.contains("Restored to previous state"));
        assertTrue(output.contains("Current content: Hello, World!"));
    }

    @Test
    @DisplayName("Should demonstrate complete memento pattern workflow")
    void testCompleteWorkflow() {
        TextEditor textEditor = new TextEditor();
        CareTaker careTaker = new CareTaker();

        // Step 1: Write initial content
        textEditor.write("Hello, World!");
        assertEquals("Hello, World!", textEditor.getContent());
        careTaker.save(textEditor);

        // Step 2: Modify content
        textEditor.write(" This is a simple text editor.");
        assertEquals(" This is a simple text editor.", textEditor.getContent());
        careTaker.save(textEditor);

        // Step 3: Undo to previous state
        careTaker.undo(textEditor);
        assertEquals("Hello, World!", textEditor.getContent());

        String output = outputStream.toString();
        assertTrue(output.contains("Restored to previous state"));
    }

    @Test
    @DisplayName("Should handle multiple edit and undo cycles")
    void testMultipleEditAndUndoCycles() {
        TextEditor textEditor = new TextEditor();
        CareTaker careTaker = new CareTaker();

        // Cycle 1
        textEditor.write("Version 1");
        careTaker.save(textEditor);

        textEditor.write("Version 2");
        careTaker.save(textEditor);

        careTaker.undo(textEditor);
        assertEquals("Version 1", textEditor.getContent());

        // Cycle 2
        textEditor.write("Version 3");
        careTaker.save(textEditor);

        textEditor.write("Version 4");
        careTaker.save(textEditor);

        careTaker.undo(textEditor);
        assertEquals("Version 3", textEditor.getContent());
    }

    @Test
    @DisplayName("Should simulate real text editing scenario")
    void testRealTextEditingScenario() {
        TextEditor textEditor = new TextEditor();
        CareTaker careTaker = new CareTaker();

        // User starts writing
        textEditor.write("Dear Sir,");
        careTaker.save(textEditor);

        // User continues
        textEditor.write("Dear Sir,\nI am writing to inform you about...");
        careTaker.save(textEditor);

        // User adds more
        textEditor.write("Dear Sir,\nI am writing to inform you about...\nBest regards,");
        careTaker.save(textEditor);

        // User decides to undo last change
        careTaker.undo(textEditor);
        assertEquals("Dear Sir,\nI am writing to inform you about...", textEditor.getContent());

        // User undoes again
        careTaker.undo(textEditor);
        assertEquals("Dear Sir,", textEditor.getContent());
    }

    @Test
    @DisplayName("Should handle empty and null content scenarios")
    void testEmptyAndNullContent() {
        TextEditor textEditor = new TextEditor();
        CareTaker careTaker = new CareTaker();

        // Start with empty
        textEditor.write("");
        careTaker.save(textEditor);
        assertEquals("", textEditor.getContent());

        // Add content
        textEditor.write("Some content");
        careTaker.save(textEditor);
        assertEquals("Some content", textEditor.getContent());

        // Undo to empty
        careTaker.undo(textEditor);
        assertEquals("", textEditor.getContent());
    }

    @Test
    @DisplayName("Should verify pattern encapsulation")
    void testPatternEncapsulation() {
        TextEditor textEditor = new TextEditor();
        CareTaker careTaker = new CareTaker();

        // Memento pattern should encapsulate the state
        textEditor.write("Encapsulated State");
        careTaker.save(textEditor);

        // Change the state
        textEditor.write("New State");

        // The careTaker should be able to restore without knowing internal details
        careTaker.save(textEditor);
        careTaker.undo(textEditor);

        assertEquals("Encapsulated State", textEditor.getContent());
    }

    @Test
    @DisplayName("Should handle stress test with many operations")
    void testStressTest() {
        TextEditor textEditor = new TextEditor();
        CareTaker careTaker = new CareTaker();

        // Perform many write and save operations
        for (int i = 0; i < 100; i++) {
            textEditor.write("Content iteration " + i);
            careTaker.save(textEditor);
        }

        assertEquals("Content iteration 99", textEditor.getContent());

        // Undo several times
        for (int i = 0; i < 10; i++) {
            careTaker.undo(textEditor);
        }

        assertEquals("Content iteration 89", textEditor.getContent());
    }

    @Test
    @DisplayName("Should demonstrate pattern benefits")
    void testPatternBenefits() {
        TextEditor textEditor = new TextEditor();
        CareTaker careTaker = new CareTaker();

        // Benefit 1: Can save state at any point
        textEditor.write("Checkpoint 1");
        careTaker.save(textEditor);

        // Benefit 2: Can make risky changes knowing we can undo
        textEditor.write("Experimental change");
        careTaker.save(textEditor);

        // Benefit 3: Can restore to any saved state
        careTaker.undo(textEditor);
        assertEquals("Checkpoint 1", textEditor.getContent());

        // Benefit 4: Original object doesn't need to expose internals
        assertNotNull(textEditor.getContent());
    }

    @Test
    @DisplayName("Should verify independence of mementos")
    void testMementoIndependence() {
        TextEditor textEditor = new TextEditor();
        CareTaker careTaker = new CareTaker();

        textEditor.write("State A");
        careTaker.save(textEditor);

        textEditor.write("State B");
        careTaker.save(textEditor);

        textEditor.write("State C");
        careTaker.save(textEditor);

        // Each memento should be independent
        careTaker.undo(textEditor);
        assertEquals("State B", textEditor.getContent());

        careTaker.undo(textEditor);
        assertEquals("State A", textEditor.getContent());
    }
}
