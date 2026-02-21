package com.codeprep;

public class JSONParser extends DataParser {

    // Overrides the hook to provide JSON-specific open behaviour
    @Override
    protected void open() {
        System.out.println("Opening JSON stream...");
    }

    @Override
    protected void doParse() {
        System.out.println("Parsing JSON data...");
    }
}
