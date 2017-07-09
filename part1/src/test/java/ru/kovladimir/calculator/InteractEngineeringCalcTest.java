package ru.kovladimir.calculator;

import org.junit.Assert;
import org.junit.Test;

public class InteractEngineeringCalcTest {

    @Test
    public void whenFindSinThenCalcHasResult() {
        double x = 180;
        double result = Math.sin(Math.toRadians(180));
        String[] commands = new String[] {"6", String.valueOf(x), "5"};
        EngineeringCalculator calc = new EngineeringCalculator();
        IOController controller = new IOController(new ConsoleOutput(), new StubInput(commands));
        int size = 8;
        InteractEngineeringCalc interactCalc = new InteractEngineeringCalc(controller, calc, size);

        interactCalc.start();
        double resultFromCalc = calc.getResult();

        Assert.assertEquals(result, resultFromCalc, 0.001);
    }

    @Test
    public void whenFindCosThenCalcHasResult() {
        double x = 180;
        double result = Math.cos(Math.toRadians(180));
        String[] commands = new String[] {"7", String.valueOf(x), "5"};
        EngineeringCalculator calc = new EngineeringCalculator();
        IOController controller = new IOController(new ConsoleOutput(), new StubInput(commands));
        int size = 8;
        InteractEngineeringCalc interactCalc = new InteractEngineeringCalc(controller, calc, size);

        interactCalc.start();
        double resultFromCalc = calc.getResult();

        Assert.assertEquals(result, resultFromCalc, 0.001);
    }

}
