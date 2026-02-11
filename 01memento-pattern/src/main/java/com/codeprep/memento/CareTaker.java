package com.codeprep.memento;

import java.util.Stack;

// CareTaker class to manage the mementos (snapshots of the Text Editors state)
public class CareTaker {

    private final Stack<EditorMemento> history = new Stack<>();

    // Method to save the current state of the Text Editor
    public void save(TextEditor textEditor) {
        history.push(textEditor.save());
    }

    // Method to restore the last saved state of the Text Editor
    public void undo(TextEditor textEditor) {
        if (!history.isEmpty()) {
            history.pop();
            if (!history.isEmpty()) {
                textEditor.restore(history.peek());
                System.out.println("Restored to previous state");
            } else {
                System.out.println("No saved state to restore.");
            }
        } else {
            System.out.println("No saved state to restore.");
        }
    }
}
