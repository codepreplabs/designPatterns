package com.codeprep;

import java.util.Objects;

// Also known as the fly-weight class
public class BulletType {

    private final String color; // Immutable intrinsic state

    public BulletType(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BulletType)) return false;
        BulletType that = (BulletType) o;
        return Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
