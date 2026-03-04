package com.codeprep;

/**
 * Concrete Decorator - Mushroom
 * Adds mushroom topping to a pizza.
 */
public class MushroomDecorator extends PizzaDecorator {

    public MushroomDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Mushrooms";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 1.25;
    }
}

