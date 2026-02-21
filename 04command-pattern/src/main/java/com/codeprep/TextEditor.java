package com.codeprep;

/**
 * Receiver - The object that performs the actual work
 * This class knows how to perform the operations required to carry out a request
 */
public class TextEditor {

    private String content = "";

    /**
     * Actual operation to make text bold
     */
    public void bold() {
        System.out.println("Making text bold");
        content = "<b>" + content + "</b>";
    }

    /**
     * Actual operation to make text italic
     */
    public void italic() {
        System.out.println("Making text italic");
        content = "<i>" + content + "</i>";
    }

    /**
     * Operation to add text
     */
    public void addText(String text) {
        System.out.println("Adding text: " + text);
        content += text;
    }

    /**
     * Get the current content
     */
    public String getContent() {
        return content;
    }

    /**
     * Clear the content
     */
    public void clear() {
        System.out.println("Clearing text");
        content = "";
    }
}
