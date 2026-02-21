package com.codeprep;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CSVParser Tests")
class CSVParserTest {

    private CSVParser csvParser;
    private ByteArrayOutputStream outputCapture;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        csvParser = new CSVParser();
        originalOut = System.out;
        outputCapture = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputCapture));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    private String getOutput() {
        return outputCapture.toString().trim();
    }

    // -----------------------------------------------------------------------
    // Type checks
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("CSVParser should be a subtype of DataParser")
    void testIsDataParser() {
        assertInstanceOf(DataParser.class, csvParser);
    }

    // -----------------------------------------------------------------------
    // Template method integration
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("parse() should print open, CSV parse and close messages")
    void testParseProducesAllThreeLines() {
        csvParser.parse();
        String output = getOutput();

        assertTrue(output.contains("Opening file..."), "Should call default open()");
        assertTrue(output.contains("Parsing CSV data..."), "Should call CSV doParse()");
        assertTrue(output.contains("Closing file..."), "Should call default close()");
    }

    @Test
    @DisplayName("parse() output lines should appear in open → parse → close order")
    void testParseOutputOrder() {
        csvParser.parse();
        String output = getOutput();

        int openIdx   = output.indexOf("Opening file...");
        int parseIdx  = output.indexOf("Parsing CSV data...");
        int closeIdx  = output.indexOf("Closing file...");

        assertTrue(openIdx < parseIdx,  "open() must come before doParse()");
        assertTrue(parseIdx < closeIdx, "doParse() must come before close()");
    }

    @Test
    @DisplayName("CSVParser should use default open() hook")
    void testUsesDefaultOpen() {
        csvParser.parse();
        String output = getOutput();
        assertTrue(output.contains("Opening file..."),
                "CSVParser should not override open(), so default message is expected");
    }

    @Test
    @DisplayName("CSVParser should use default close() hook")
    void testUsesDefaultClose() {
        csvParser.parse();
        String output = getOutput();
        assertTrue(output.contains("Closing file..."),
                "CSVParser should not override close(), so default message is expected");
    }

    @Test
    @DisplayName("parse() can be called multiple times independently")
    void testParseIsIdempotent() {
        csvParser.parse();
        outputCapture.reset();
        csvParser.parse();
        String output = getOutput();

        assertTrue(output.contains("Parsing CSV data..."),
                "Second call to parse() should still work correctly");
    }
}



