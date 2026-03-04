package com.codeprep;

/**
 * Abstract Decorator
 * Wraps a Pizza object and delegates calls to it.
 * All concrete decorators extend this class.
 */
public abstract class PizzaDecorator implements Pizza {

    protected final Pizza pizza;

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }

    @Override
    public double getCost() {
        return pizza.getCost();
    }
}

