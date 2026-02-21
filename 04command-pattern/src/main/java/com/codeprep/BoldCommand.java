package com.codeprep;

/**
 * Concrete Command - Defines a binding between a Receiver object and an action
 * Implements execute by invoking the corresponding operation(s) on Receiver
 */
public class BoldCommand implements Command {

    private final TextEditor textEditor;

    public BoldCommand(TextEditor textEditor) {
        this.textEditor = textEditor;
    }

    @Override
    public void execute() {
        textEditor.bold();
    }
}
