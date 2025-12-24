package org.escamilla.calculator;

import org.escamilla.annotations.Test;

public final class CalculatorTest implements UnitTest {
    public final Calculator calculator = new Calculator();

    @Override
    public void beforeEachTest() {
        calculator.registerOperation("+", Integer::sum);
        calculator.registerOperation("-", (a, b) -> a - b);
        calculator.registerOperation("/", (a, b) -> a / b);
        calculator.registerOperation("*", (a, b) -> a * b);
    }

    @Test
    public void testAddition() {
        assert calculator.calculate(1, "+", 1) == 2;
        System.out.println("test passed");
    }

    @Test
    public void testSubstraction() {
        assert calculator.calculate(45, "-", 43) == 1;
        System.out.println("test failed");
    }
}
