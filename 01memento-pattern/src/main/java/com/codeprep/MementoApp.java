package com.codeprep;

import com.codeprep.memento.CareTaker;
import com.codeprep.memento.TextEditor;

public class MementoApp {
    public static void main(String[] args) {

        TextEditor textEditor = new TextEditor();
        CareTaker careTaker = new CareTaker();

        // Initial state of the text editor
        textEditor.write("Hello, World!");
        careTaker.save(textEditor);

        // Adding more content
        textEditor.write(" This is a simple text editor.");
        careTaker.save(textEditor);

        careTaker.undo(textEditor);
        System.out.println("Current content: " + textEditor.getContent());
    }
}