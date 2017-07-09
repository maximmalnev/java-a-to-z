package ru.kovladimir.calculator;

import org.junit.Assert;
import org.junit.Test;

public class EngineeringCalculatorTest {

    @Test
    public void WhenFinSinThenResultContainsRightAnswer() {
        EngineeringCalculator calculator = new EngineeringCalculator();
        double angle = Math.toRadians(180);
        double result = Math.sin(angle);

        calculator.sin(angle);
        double resultFromCalculator = calculator.getResult();

        Assert.assertEquals(result, resultFromCalculator, 0.001);
    }

    @Test
    public void WhenFinCosThenResultContainsRightAnswer() {
        EngineeringCalculator calculator = new EngineeringCalculator();
        double angle = Math.toRadians(180);
        double result = Math.cos(angle);

        calculator.cos(angle);
        double resultFromCalculator = calculator.getResult();

        Assert.assertEquals(result, resultFromCalculator, 0.001);
    }
}
