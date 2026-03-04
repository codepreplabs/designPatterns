package com.codeprep;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BulletTypeTest {

    @Test
    void getColor_returnsCorrectColor() {
        BulletType bulletType = new BulletType("blue");
        assertEquals("blue", bulletType.getColor());
    }

    @Test
    void equals_samColor_returnsTrue() {
        BulletType a = new BulletType("red");
        BulletType b = new BulletType("red");
        assertEquals(a, b);
    }

    @Test
    void equals_differentColor_returnsFalse() {
        BulletType a = new BulletType("red");
        BulletType b = new BulletType("blue");
        assertNotEquals(a, b);
    }

    @Test
    void equals_sameInstance_returnsTrue() {
        BulletType a = new BulletType("green");
        assertEquals(a, a);
    }

    @Test
    void equals_null_returnsFalse() {
        BulletType a = new BulletType("red");
        assertNotEquals(null, a);
    }

    @Test
    void equals_differentType_returnsFalse() {
        BulletType a = new BulletType("red");
        assertNotEquals("red", a);
    }

    @Test
    void hashCode_sameColor_returnsSameHash() {
        BulletType a = new BulletType("red");
        BulletType b = new BulletType("red");
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void hashCode_differentColor_returnsDifferentHash() {
        BulletType a = new BulletType("red");
        BulletType b = new BulletType("blue");
        assertNotEquals(a.hashCode(), b.hashCode());
    }
}

