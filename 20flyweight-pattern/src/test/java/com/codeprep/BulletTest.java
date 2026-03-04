package com.codeprep;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BulletTest {

    @Test
    void bullet_createsWithoutException() {
        assertDoesNotThrow(() -> new Bullet("red", 10, 20, 5));
    }

    @Test
    void display_containsColor() {
        Bullet bullet = new Bullet("red", 10, 20, 5);
        String output = captureDisplay(bullet);
        assertTrue(output.contains("red"));
    }

    @Test
    void display_containsX() {
        Bullet bullet = new Bullet("red", 10, 20, 5);
        String output = captureDisplay(bullet);
        assertTrue(output.contains("10"));
    }

    @Test
    void display_containsY() {
        Bullet bullet = new Bullet("red", 10, 20, 5);
        String output = captureDisplay(bullet);
        assertTrue(output.contains("20"));
    }

    @Test
    void display_containsVelocity() {
        Bullet bullet = new Bullet("red", 10, 20, 5);
        String output = captureDisplay(bullet);
        assertTrue(output.contains("5"));
    }

    @Test
    void display_fullOutput() {
        Bullet bullet = new Bullet("blue", 100, 200, 30);
        String output = captureDisplay(bullet);
        assertEquals("Bullet [color=blue, x=100, y=200, velocity=30]", output.trim());
    }

    @Test
    void bullets_sameColor_shareTheSameBulletType() {
        // Verify flyweight sharing at the Bullet level via factory
        BulletType type1 = BulletTypeFactory.getBulletType("purple");
        BulletType type2 = BulletTypeFactory.getBulletType("purple");
        assertSame(type1, type2);
    }

    @Test
    void bullet_zeroValues_displaysCorrectly() {
        Bullet bullet = new Bullet("red", 0, 0, 0);
        String output = captureDisplay(bullet);
        assertEquals("Bullet [color=red, x=0, y=0, velocity=0]", output.trim());
    }

    // --- helper ---

    private String captureDisplay(Bullet bullet) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(baos));
        try {
            bullet.display();
        } finally {
            System.setOut(original);
        }
        return baos.toString();
    }
}

