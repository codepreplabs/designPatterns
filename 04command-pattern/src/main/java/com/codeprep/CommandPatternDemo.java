package com.codeprep;

/**
 * Client - Creates ConcreteCommand objects and sets their receiver
 * Demonstrates the Command Pattern usage
 */
public class CommandPatternDemo {
    public static void main(String[] args) {
        // Create the receiver
        TextEditor textEditor = new TextEditor();

        // Add some initial text
        textEditor.addText("Hello World");
        System.out.println("Content: " + textEditor.getContent());
        System.out.println();

        // Create commands
        Command boldCommand = new BoldCommand(textEditor);
        Command italicCommand = new ItalicCommand(textEditor);

        // Create invokers (buttons) and associate them with commands
        Button boldButton = new Button(boldCommand);
        Button italicButton = new Button(italicCommand);

        // Execute commands via invokers
        System.out.println("Clicking Bold Button:");
        boldButton.onClick();
        System.out.println("Content: " + textEditor.getContent());
        System.out.println();

        System.out.println("Clicking Italic Button:");
        italicButton.onClick();
        System.out.println("Content: " + textEditor.getContent());
        System.out.println();

        // Demonstrate dynamic command reassignment
        System.out.println("Reassigning boldButton to execute italic command:");
        boldButton.setCommand(italicCommand);
        boldButton.onClick();
        System.out.println("Final Content: " + textEditor.getContent());
    }
}
