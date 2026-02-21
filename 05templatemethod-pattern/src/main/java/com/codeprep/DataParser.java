package com.codeprep;

public abstract class DataParser {

    // Template Method — defines the algorithm skeleton; subclasses cannot alter this order
    public final void parse() {
        open();
        doParse();
        close();
    }

    // Common step with a default implementation (hook — subclasses may override)
    protected void open() {
        System.out.println("Opening file...");
    }

    // Abstract step — each subclass MUST provide its own parsing logic
    protected abstract void doParse();

    // Common step with a default implementation (hook — subclasses may override)
    protected void close() {
        System.out.println("Closing file...");
    }
}
