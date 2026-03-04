package com.codeprep;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BulletTypeFactoryTest {

    @Test
    void getBulletType_returnsNonNull() {
        BulletType bulletType = BulletTypeFactory.getBulletType("red");
        assertNotNull(bulletType);
    }

    @Test
    void getBulletType_returnsCorrectColor() {
        BulletType bulletType = BulletTypeFactory.getBulletType("green");
        assertEquals("green", bulletType.getColor());
    }

    @Test
    void getBulletType_sameColor_returnsSameInstance() {
        // Core flyweight guarantee: same color => same shared object
        BulletType first = BulletTypeFactory.getBulletType("yellow");
        BulletType second = BulletTypeFactory.getBulletType("yellow");
        assertSame(first, second);
    }

    @Test
    void getBulletType_differentColors_returnsDifferentInstances() {
        BulletType red = BulletTypeFactory.getBulletType("red");
        BulletType blue = BulletTypeFactory.getBulletType("blue");
        assertNotSame(red, blue);
    }

    @Test
    void getBulletType_multipleColors_eachCachedSeparately() {
        BulletType red1 = BulletTypeFactory.getBulletType("red");
        BulletType blue1 = BulletTypeFactory.getBulletType("blue");
        BulletType red2 = BulletTypeFactory.getBulletType("red");
        BulletType blue2 = BulletTypeFactory.getBulletType("blue");

        assertSame(red1, red2);
        assertSame(blue1, blue2);
        assertNotSame(red1, blue1);
    }
}

