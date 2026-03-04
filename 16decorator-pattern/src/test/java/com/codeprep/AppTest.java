package com.codeprep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Pizza Decorator Pattern.
 *
 * Covers:
 *  - PlainPizza (base component)
 *  - Each individual decorator
 *  - Stacked / combined decorators
 *  - Duplicate decorators (e.g. double cheese)
 */
@DisplayName("Pizza Decorator Pattern Tests")
public class AppTest {

    // -----------------------------------------------------------------------
    // Base component
    // -----------------------------------------------------------------------
    @Nested
    @DisplayName("PlainPizza")
    class PlainPizzaTests {

        private Pizza pizza;

        @BeforeEach
        void setUp() {
            pizza = new PlainPizza();
        }

        @Test
        @DisplayName("should have correct base description")
        void testDescription() {
            assertEquals("Plain Pizza (dough + tomato sauce)", pizza.getDescription());
        }

        @Test
        @DisplayName("should have base cost of $5.00")
        void testCost() {
            assertEquals(5.00, pizza.getCost(), 0.001);
        }
    }

    // -----------------------------------------------------------------------
    // Individual decorators
    // -----------------------------------------------------------------------
    @Nested
    @DisplayName("CheeseDecorator")
    class CheeseDecoratorTests {

        private Pizza pizza;

        @BeforeEach
        void setUp() {
            pizza = new CheeseDecorator(new PlainPizza());
        }

        @Test
        @DisplayName("should append ', Cheese' to description")
        void testDescription() {
            assertTrue(pizza.getDescription().contains("Cheese"));
        }

        @Test
        @DisplayName("should add $1.50 to base cost")
        void testCost() {
            assertEquals(6.50, pizza.getCost(), 0.001);
        }
    }

    @Nested
    @DisplayName("PepperoniDecorator")
    class PepperoniDecoratorTests {

        private Pizza pizza;

        @BeforeEach
        void setUp() {
            pizza = new PepperoniDecorator(new PlainPizza());
        }

        @Test
        @DisplayName("should append ', Pepperoni' to description")
        void testDescription() {
            assertTrue(pizza.getDescription().contains("Pepperoni"));
        }

        @Test
        @DisplayName("should add $2.00 to base cost")
        void testCost() {
            assertEquals(7.00, pizza.getCost(), 0.001);
        }
    }

    @Nested
    @DisplayName("MushroomDecorator")
    class MushroomDecoratorTests {

        private Pizza pizza;

        @BeforeEach
        void setUp() {
            pizza = new MushroomDecorator(new PlainPizza());
        }

        @Test
        @DisplayName("should append ', Mushrooms' to description")
        void testDescription() {
            assertTrue(pizza.getDescription().contains("Mushrooms"));
        }

        @Test
        @DisplayName("should add $1.25 to base cost")
        void testCost() {
            assertEquals(6.25, pizza.getCost(), 0.001);
        }
    }

    @Nested
    @DisplayName("OlivesDecorator")
    class OlivesDecoratorTests {

        private Pizza pizza;

        @BeforeEach
        void setUp() {
            pizza = new OlivesDecorator(new PlainPizza());
        }

        @Test
        @DisplayName("should append ', Olives' to description")
        void testDescription() {
            assertTrue(pizza.getDescription().contains("Olives"));
        }

        @Test
        @DisplayName("should add $1.00 to base cost")
        void testCost() {
            assertEquals(6.00, pizza.getCost(), 0.001);
        }
    }

    // -----------------------------------------------------------------------
    // Stacked / combined decorators
    // -----------------------------------------------------------------------
    @Nested
    @DisplayName("Stacked Decorators")
    class StackedDecoratorTests {

        @Test
        @DisplayName("Cheese + Pepperoni: description should contain both toppings")
        void testCheesePepperoniDescription() {
            Pizza pizza = new PepperoniDecorator(new CheeseDecorator(new PlainPizza()));
            String desc = pizza.getDescription();
            assertTrue(desc.contains("Cheese"));
            assertTrue(desc.contains("Pepperoni"));
        }

        @Test
        @DisplayName("Cheese + Pepperoni: cost should be $5.00 + $1.50 + $2.00 = $8.50")
        void testCheesePepperoniCost() {
            Pizza pizza = new PepperoniDecorator(new CheeseDecorator(new PlainPizza()));
            assertEquals(8.50, pizza.getCost(), 0.001);
        }

        @Test
        @DisplayName("All toppings: description should contain all four toppings")
        void testAllToppingsDescription() {
            Pizza pizza = new OlivesDecorator(
                             new MushroomDecorator(
                                 new PepperoniDecorator(
                                     new CheeseDecorator(new PlainPizza()))));
            String desc = pizza.getDescription();
            assertTrue(desc.contains("Cheese"));
            assertTrue(desc.contains("Pepperoni"));
            assertTrue(desc.contains("Mushrooms"));
            assertTrue(desc.contains("Olives"));
        }

        @Test
        @DisplayName("All toppings: cost should be $5.00 + $1.50 + $2.00 + $1.25 + $1.00 = $10.75")
        void testAllToppingsCost() {
            Pizza pizza = new OlivesDecorator(
                             new MushroomDecorator(
                                 new PepperoniDecorator(
                                     new CheeseDecorator(new PlainPizza()))));
            assertEquals(10.75, pizza.getCost(), 0.001);
        }

        @Test
        @DisplayName("Double cheese: cost should be $5.00 + $1.50 + $1.50 = $8.00")
        void testDoubleCheeseIncrementsCostTwice() {
            Pizza pizza = new CheeseDecorator(new CheeseDecorator(new PlainPizza()));
            assertEquals(8.00, pizza.getCost(), 0.001);
        }

        @Test
        @DisplayName("Double cheese: description should contain 'Cheese' twice")
        void testDoubleCheeseDescriptionAppearedTwice() {
            Pizza pizza = new CheeseDecorator(new CheeseDecorator(new PlainPizza()));
            String desc = pizza.getDescription();
            int firstIndex  = desc.indexOf("Cheese");
            int secondIndex = desc.indexOf("Cheese", firstIndex + 1);
            assertNotEquals(-1, secondIndex, "Expected 'Cheese' to appear twice in description");
        }

        @Test
        @DisplayName("Double cheese + Mushroom + Olives: cost should be $10.25")
        void testDeluxePizzaCost() {
            Pizza pizza = new OlivesDecorator(
                             new MushroomDecorator(
                                 new CheeseDecorator(
                                     new CheeseDecorator(new PlainPizza()))));
            assertEquals(10.25, pizza.getCost(), 0.001);
        }
    }
}
