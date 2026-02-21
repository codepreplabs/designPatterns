package com.codeprep;

/**
 * Advanced example showing undo/redo support in Command Pattern
 * This demonstrates how to implement reversible commands
 */
public class ClearCommand implements Command {

    private final TextEditor textEditor;
    private String previousContent;

    public ClearCommand(TextEditor textEditor) {
        this.textEditor = textEditor;
    }

    @Override
    public void execute() {
        // Save state before executing for potential undo
        previousContent = textEditor.getContent();
        textEditor.clear();
    }

    /**
     * Undo the clear operation by restoring previous content
     * This would require adding an undo() method to the Command interface
     */
    public void undo() {
        System.out.println("Undoing clear operation");
        // Would need to add setContent method to TextEditor
        // textEditor.setContent(previousContent);
    }
}

