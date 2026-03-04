package com.codeprep;

/**
 * Concrete Decorator - Cheese
 * Adds cheese topping to a pizza.
 */
public class CheeseDecorator extends PizzaDecorator {

    public CheeseDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Cheese";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 1.50;
    }
}

