package com.codeprep;

/**
 * Concrete Component
 * The base pizza with just dough and sauce. This is the object being decorated.
 */
public class PlainPizza implements Pizza {

    @Override
    public String getDescription() {
        return "Plain Pizza (dough + tomato sauce)";
    }

    @Override
    public double getCost() {
        return 5.00;
    }
}

