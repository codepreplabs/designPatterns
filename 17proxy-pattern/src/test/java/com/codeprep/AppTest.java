package com.codeprep;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Proxy Pattern implementation.
 *
 * Covers:
 *  - RealImage  : eager loading on construction, display output
 *  - ProxyImage : lazy loading (loads only on first display), caching (loads only once)
 *  - Image interface : both RealImage and ProxyImage honour the same contract
 */
@DisplayName("Proxy Pattern Tests")
public class AppTest {

    // -----------------------------------------------------------------------
    // Helpers – capture System.out so we can assert on printed output
    // -----------------------------------------------------------------------

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream originalOut;

    @BeforeEach
    void redirectOutput() {
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreOutput() {
        System.setOut(originalOut);
    }

    private String output() {
        return outContent.toString();
    }

    // -----------------------------------------------------------------------
    // RealImage
    // -----------------------------------------------------------------------
    @Nested
    @DisplayName("RealImage")
    class RealImageTests {

        @Test
        @DisplayName("should load image from disk immediately on construction")
        void testLoadsFromDiskOnConstruction() {
            new RealImage("photo.jpg");
            assertTrue(output().contains("Loading image from disk: photo.jpg"),
                    "Expected 'Loading image from disk: photo.jpg' in output");
        }

        @Test
        @DisplayName("should include the file name in the loading message")
        void testFileNameInLoadMessage() {
            new RealImage("landscape.png");
            assertTrue(output().contains("landscape.png"),
                    "Expected file name 'landscape.png' in loading output");
        }

        @Test
        @DisplayName("display() should print the display message")
        void testDisplay() {
            RealImage image = new RealImage("photo.jpg");
            outContent.reset();                     // clear the load message
            image.display();
            assertTrue(output().contains("Displaying image"),
                    "Expected 'Displaying image' in output");
        }

        @Test
        @DisplayName("display() can be called multiple times")
        void testDisplayMultipleTimes() {
            RealImage image = new RealImage("photo.jpg");
            outContent.reset();
            image.display();
            image.display();
            long count = output().lines()
                    .filter(line -> line.contains("Displaying image"))
                    .count();
            assertEquals(2, count, "Expected display message to appear twice");
        }

        @Test
        @DisplayName("should implement the Image interface")
        void testImplementsImage() {
            Image image = new RealImage("photo.jpg");
            assertNotNull(image);
            assertInstanceOf(Image.class, image);
        }
    }

    // -----------------------------------------------------------------------
    // ProxyImage – lazy loading
    // -----------------------------------------------------------------------
    @Nested
    @DisplayName("ProxyImage – lazy loading")
    class ProxyImageLazyLoadingTests {

        @Test
        @DisplayName("should NOT load image from disk on construction")
        void testNoLoadOnConstruction() {
            new ProxyImage("photo.jpg");
            assertFalse(output().contains("Loading image from disk"),
                    "ProxyImage must NOT load from disk until display() is called");
        }

        @Test
        @DisplayName("should load image from disk on first display() call")
        void testLoadsOnFirstDisplay() {
            Image image = new ProxyImage("photo.jpg");
            image.display();
            assertTrue(output().contains("Loading image from disk: photo.jpg"),
                    "Expected disk-load message on first display()");
        }

        @Test
        @DisplayName("should print the display message after loading")
        void testDisplayMessageAfterLoad() {
            Image image = new ProxyImage("photo.jpg");
            image.display();
            assertTrue(output().contains("Displaying image"),
                    "Expected 'Displaying image' message after loading");
        }
    }

    // -----------------------------------------------------------------------
    // ProxyImage – caching (loads only once)
    // -----------------------------------------------------------------------
    @Nested
    @DisplayName("ProxyImage – caching")
    class ProxyImageCachingTests {

        @Test
        @DisplayName("should load image from disk only once across multiple display() calls")
        void testLoadsOnlyOnce() {
            Image image = new ProxyImage("photo.jpg");
            image.display();
            image.display();
            image.display();

            long loadCount = output().lines()
                    .filter(line -> line.contains("Loading image from disk"))
                    .count();
            assertEquals(1, loadCount,
                    "RealImage should be created (disk load) exactly once, regardless of display() call count");
        }

        @Test
        @DisplayName("should call display() the correct number of times")
        void testDisplayCalledMultipleTimes() {
            Image image = new ProxyImage("photo.jpg");
            image.display();
            image.display();

            long displayCount = output().lines()
                    .filter(line -> line.contains("Displaying image"))
                    .count();
            assertEquals(2, displayCount,
                    "Expected 'Displaying image' to appear for every display() call");
        }
    }

    // -----------------------------------------------------------------------
    // Image interface contract
    // -----------------------------------------------------------------------
    @Nested
    @DisplayName("Image interface contract")
    class ImageInterfaceTests {

        @Test
        @DisplayName("ProxyImage should implement the Image interface")
        void testProxyImageImplementsImage() {
            Image image = new ProxyImage("photo.jpg");
            assertNotNull(image);
            assertInstanceOf(Image.class, image);
        }

        @Test
        @DisplayName("Both RealImage and ProxyImage are interchangeable via the Image interface")
        void testPolymorphism() {
            Image[] images = {
                new RealImage("real.jpg"),
                new ProxyImage("proxy.jpg")
            };

            outContent.reset();
            for (Image img : images) {
                img.display();
            }

            long displayCount = output().lines()
                    .filter(line -> line.contains("Displaying image"))
                    .count();
            assertEquals(2, displayCount,
                    "Both implementations should produce a 'Displaying image' line");
        }

        @Test
        @DisplayName("ProxyImage with different file names are independent")
        void testIndependentProxies() {
            Image proxy1 = new ProxyImage("file1.jpg");
            Image proxy2 = new ProxyImage("file2.jpg");

            proxy1.display();
            proxy2.display();

            assertTrue(output().contains("file1.jpg"), "Expected file1.jpg in output");
            assertTrue(output().contains("file2.jpg"), "Expected file2.jpg in output");
        }
    }
}
