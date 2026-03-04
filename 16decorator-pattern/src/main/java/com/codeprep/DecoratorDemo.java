package com.codeprep;

/**
 * Decorator Pattern - Pizza Example
 *
 * Pattern roles:
 *   Component Interface : Pizza
 *   Concrete Component  : PlainPizza
 *   Abstract Decorator  : PizzaDecorator
 *   Concrete Decorators : CheeseDecorator, PepperoniDecorator,
 *                         MushroomDecorator, OlivesDecorator
 *
 * Key idea: decorators wrap a Pizza and add behaviour (toppings + cost)
 * without changing the original class or creating a subclass explosion.
 */
public class DecoratorDemo {

    public static void main(String[] args) {

        printDivider("Plain Pizza");
        Pizza pizza = new PlainPizza();
        printPizza(pizza);

        // --- Order 1: Cheese + Pepperoni ---
        printDivider("Cheese & Pepperoni Pizza");
        Pizza cheesePepperoni = new PepperoniDecorator(
                                    new CheeseDecorator(
                                        new PlainPizza()));
        printPizza(cheesePepperoni);

        // --- Order 2: Double cheese + Mushrooms + Olives ---
        printDivider("Double Cheese, Mushroom & Olive Pizza");
        Pizza deluxe = new OlivesDecorator(
                           new MushroomDecorator(
                               new CheeseDecorator(
                                   new CheeseDecorator(
                                       new PlainPizza()))));
        printPizza(deluxe);

        // --- Order 3: The works ---
        printDivider("The Works (all toppings)");
        Pizza theWorks = new OlivesDecorator(
                             new MushroomDecorator(
                                 new PepperoniDecorator(
                                     new CheeseDecorator(
                                         new PlainPizza()))));
        printPizza(theWorks);
    }

    private static void printPizza(Pizza pizza) {
        System.out.println("Description : " + pizza.getDescription());
        System.out.printf ("Total cost   : $%.2f%n", pizza.getCost());
    }

    private static void printDivider(String title) {
        System.out.println("\n--- " + title + " ---");
    }
}

