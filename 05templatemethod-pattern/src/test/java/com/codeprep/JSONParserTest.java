package com.codeprep;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JSONParser Tests")
class JSONParserTest {

    private JSONParser jsonParser;
    private ByteArrayOutputStream outputCapture;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        jsonParser = new JSONParser();
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
    @DisplayName("JSONParser should be a subtype of DataParser")
    void testIsDataParser() {
        assertInstanceOf(DataParser.class, jsonParser);
    }

    // -----------------------------------------------------------------------
    // Hook override
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("JSONParser should use its overridden open() hook")
    void testOverridesOpenHook() {
        jsonParser.parse();
        String output = getOutput();

        assertTrue(output.contains("Opening JSON stream..."),
                "JSONParser overrides open(), so custom message is expected");
        assertFalse(output.contains("Opening file..."),
                "Default open() message should NOT appear for JSONParser");
    }

    @Test
    @DisplayName("JSONParser should use default close() hook")
    void testUsesDefaultClose() {
        jsonParser.parse();
        String output = getOutput();
        assertTrue(output.contains("Closing file..."),
                "JSONParser does not override close(), so default message is expected");
    }

    // -----------------------------------------------------------------------
    // Template method integration
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("parse() should print open, JSON parse and close messages")
    void testParseProducesAllThreeLines() {
        jsonParser.parse();
        String output = getOutput();

        assertTrue(output.contains("Opening JSON stream..."), "Should call overridden open()");
        assertTrue(output.contains("Parsing JSON data..."),   "Should call JSON doParse()");
        assertTrue(output.contains("Closing file..."),         "Should call default close()");
    }

    @Test
    @DisplayName("parse() output lines should appear in open → parse → close order")
    void testParseOutputOrder() {
        jsonParser.parse();
        String output = getOutput();

        int openIdx   = output.indexOf("Opening JSON stream...");
        int parseIdx  = output.indexOf("Parsing JSON data...");
        int closeIdx  = output.indexOf("Closing file...");

        assertTrue(openIdx < parseIdx,  "open() must come before doParse()");
        assertTrue(parseIdx < closeIdx, "doParse() must come before close()");
    }

    @Test
    @DisplayName("parse() can be called multiple times independently")
    void testParseIsIdempotent() {
        jsonParser.parse();
        outputCapture.reset();
        jsonParser.parse();
        String output = getOutput();

        assertTrue(output.contains("Parsing JSON data..."),
                "Second call to parse() should still work correctly");
    }
}


