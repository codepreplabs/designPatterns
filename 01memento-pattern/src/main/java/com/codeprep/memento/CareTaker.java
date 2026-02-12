package com.codeprep.memento;

import java.util.ArrayList;
import java.util.List;

// CareTaker class to manage the mementos (snapshots of the Text Editors state)
public class CareTaker {

    private final List<EditorMemento> history = new ArrayList<>();
    private int currentIndex = -1; // Points to the current state in history

    // Method to save the current state of the Text Editor
    public void save(TextEditor textEditor) {
        // Remove any states after current index (when saving after undo)
        if (currentIndex < history.size() - 1) {
            history.subList(currentIndex + 1, history.size()).clear();
        }

        history.add(textEditor.save());
        currentIndex++;
    }

    // Method to restore the last saved state of the Text Editor
    public void undo(TextEditor textEditor) {
        if (currentIndex > 0) {
            currentIndex--;
            textEditor.restore(history.get(currentIndex));
            System.out.println("Restored to previous state");
        } else {
            System.out.println("No saved state to restore.");
        }
    }

    // Method to redo to the next saved state
    public void redo(TextEditor textEditor) {
        if (currentIndex < history.size() - 1) {
            currentIndex++;
            textEditor.restore(history.get(currentIndex));
            System.out.println("Redone to next state");
        } else {
            System.out.println("No state to redo.");
        }
    }

    // Get the size of history
    public int getHistorySize() {
        return history.size();
    }

    // Get current position in history
    public int getCurrentIndex() {
        return currentIndex;
    }

    // Check if undo is possible
    public boolean canUndo() {
        return currentIndex > 0;
    }

    // Check if redo is possible
    public boolean canRedo() {
        return currentIndex < history.size() - 1;
    }
}
