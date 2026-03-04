package com.codeprep;

/**
 * Component Interface
 * Defines the contract that both concrete components and decorators must implement.
 */
public interface Pizza {

    String getDescription();

    double getCost();
}

