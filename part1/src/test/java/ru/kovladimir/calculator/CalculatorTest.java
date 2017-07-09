package ru.kovladimir.calculator;


import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

    @Test
    public void whenAddTwoNumbersThenResultContainsTheirSum() {
        Calculator calculator = new Calculator();
        double x = 2.0;
        double y = 5.0;
        double result = x + y;

        calculator.add(x, y);
        double resultFromCalculator = calculator.getResult();

        Assert.assertEquals(result, resultFromCalculator, 0.001);
    }

    @Test
    public void whenSubtractTwoNumbersThenResultContainsTheirSubtraction() {
        Calculator calculator = new Calculator();
        double x = 5.0;
        double y = 2.0;
        double result = x - y;

        calculator.subtract(x, y);
        double resultFromCalculator = calculator.getResult();

        Assert.assertEquals(result, resultFromCalculator, 0.001);
    }

    @Test
    public void whenMultiplyTwoNumbersThenResultContainsTheirMultiplication() {
        Calculator calculator = new Calculator();
        double x = 5.0;
        double y = 2.0;
        double result = x * y;

        calculator.multiply(x, y);
        double resultFromCalculator = calculator.getResult();

        Assert.assertEquals(result, resultFromCalculator, 0.001);
    }

    @Test
    public void whenDivideTwoNumbersThenResultContainsTheirQuotient() {
        Calculator calculator = new Calculator();
        double x = 5.0;
        double y = 2.0;
        double result = x / y;

        calculator.divide(x, y);
        double resultFromCalculator = calculator.getResult();

        Assert.assertEquals(result, resultFromCalculator, 0.001);
    }
}
