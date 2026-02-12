package com.codeprep;

import com.codeprep.memento.CareTaker;
import com.codeprep.memento.TextEditor;

/**
 * Demonstration of the Memento Pattern with Undo/Redo functionality
 */
public class MementoDemo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        CareTaker careTaker = new CareTaker();

        System.out.println("=== Memento Pattern Demo: Undo/Redo Functionality ===\n");

        // Write and save states
        System.out.println("1. Writing 'Hello'");
        editor.write("Hello");
        careTaker.save(editor);
        System.out.println("   Current content: " + editor.getContent());
        System.out.println("   History size: " + careTaker.getHistorySize() + "\n");

        System.out.println("2. Writing 'Hello World'");
        editor.write("Hello World");
        careTaker.save(editor);
        System.out.println("   Current content: " + editor.getContent());
        System.out.println("   History size: " + careTaker.getHistorySize() + "\n");

        System.out.println("3. Writing 'Hello World!!!'");
        editor.write("Hello World!!!");
        careTaker.save(editor);
        System.out.println("   Current content: " + editor.getContent());
        System.out.println("   History size: " + careTaker.getHistorySize() + "\n");

        // Undo operations
        System.out.println("4. Performing UNDO");
        careTaker.undo(editor);
        System.out.println("   Current content: " + editor.getContent());
        System.out.println("   Can undo: " + careTaker.canUndo());
        System.out.println("   Can redo: " + careTaker.canRedo() + "\n");

        System.out.println("5. Performing another UNDO");
        careTaker.undo(editor);
        System.out.println("   Current content: " + editor.getContent());
        System.out.println("   Can undo: " + careTaker.canUndo());
        System.out.println("   Can redo: " + careTaker.canRedo() + "\n");

        // Redo operations
        System.out.println("6. Performing REDO");
        careTaker.redo(editor);
        System.out.println("   Current content: " + editor.getContent());
        System.out.println("   Can undo: " + careTaker.canUndo());
        System.out.println("   Can redo: " + careTaker.canRedo() + "\n");

        System.out.println("7. Performing another REDO");
        careTaker.redo(editor);
        System.out.println("   Current content: " + editor.getContent());
        System.out.println("   Can undo: " + careTaker.canUndo());
        System.out.println("   Can redo: " + careTaker.canRedo() + "\n");

        // Test redo limit
        System.out.println("8. Trying to REDO beyond limit");
        careTaker.redo(editor);
        System.out.println("   Current content: " + editor.getContent() + "\n");

        // Test branch scenario (write after undo)
        System.out.println("9. Performing UNDO twice");
        careTaker.undo(editor);
        careTaker.undo(editor);
        System.out.println("   Current content: " + editor.getContent() + "\n");

        System.out.println("10. Writing 'Hello Java' (this clears redo history)");
        editor.write("Hello Java");
        careTaker.save(editor);
        System.out.println("    Current content: " + editor.getContent());
        System.out.println("    Can redo: " + careTaker.canRedo());
        System.out.println("    History size: " + careTaker.getHistorySize() + "\n");

        System.out.println("=== Demo Complete ===");
    }
}
