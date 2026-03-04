package com.codeprep;

public class Bullet {

    private final BulletType bulletType; // Intrinsic property shared by all bullets

    private final int x; // Extrinsic properties unique to each bullet
    private final int y;
    private final int velocity;

    public Bullet(String color, int x, int y, int velocity) {
        this.bulletType = BulletTypeFactory.getBulletType(color);
        this.x = x;
        this.y = y;
        this.velocity = velocity;
    }

    public void display() {
        System.out.println("Bullet [color=" + bulletType.getColor() + ", x=" + x + ", y=" + y + ", velocity=" + velocity + "]");
    }
}
