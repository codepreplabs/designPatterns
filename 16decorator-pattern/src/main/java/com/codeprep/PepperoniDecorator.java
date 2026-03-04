package com.codeprep;

/**
 * Concrete Decorator - Pepperoni
 * Adds pepperoni topping to a pizza.
 */
public class PepperoniDecorator extends PizzaDecorator {

    public PepperoniDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Pepperoni";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 2.00;
    }
}

