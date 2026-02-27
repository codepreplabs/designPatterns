package com.codeprep;

public class BuilderDemo
{
    public static void main( String[] args )
    {
        House house = new House.HouseBuilder("Concrete", "Brick", "Roof")
                .setGarage(true)
                .setSwimmingPool(true)
                .setGarden(true)
                .build();
        System.out.println(house);
    }
}
