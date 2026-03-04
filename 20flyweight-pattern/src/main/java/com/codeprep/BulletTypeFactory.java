package com.codeprep;

import java.util.HashMap;

public class BulletTypeFactory {

    private static final HashMap<String, BulletType> BULLET_TYPES = new HashMap<>();

    public static BulletType getBulletType(String color) {
        return BULLET_TYPES.computeIfAbsent(color, BulletType::new);
    }
}
