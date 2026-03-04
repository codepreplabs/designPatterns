package com.codeprep;

public class FlyWeightDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Bullet bullet = new Bullet("red", i * 10, i * 12, i * 3);
            bullet.display();
        }
    }
}
