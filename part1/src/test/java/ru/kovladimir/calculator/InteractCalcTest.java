package ru.kovladimir.calculator;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class InteractCalcTest {

    @Test
    public void whenShowResultThenPrintIt() {
        String[] commands = new String[] {"0", "5"};
        Calculator calc = new Calculator();
        IOController controller = new IOController(new ConsoleOutput(), new StubInput(commands));
        int size = 6;
        InteractCalc interactCalc = new InteractCalc(controller, calc, size);
        PrintStream stdout = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String separator = System.lineSeparator();
        String result = "0. Show result." + separator +
                "1. Add." + separator +
                "2. Subtract." + separator +
                "3. Multiplication." + separator +
                "4. Division." + separator +
                "5. Quit." + separator +
                "Select action:" + separator +
                "0.0" + separator +
                "0. Show result." + separator +
                "1. Add." + separator +
                "2. Subtract." + separator +
                "3. Multiplication." + separator +
                "4. Division." + separator +
                "5. Quit." + separator +
                "Select action:" + separator;

        interactCalc.start();
        System.setOut(stdout);

        Assert.assertEquals(result, outContent.toString());
    }

    @Test
    public void whenAddNumbersThenCalcHasResult() {
        double x = 6.0;
        double y = 2.0;
        double result = x + y;
        String[] commands = new String[] {"1", String.valueOf(x), String.valueOf(y), "5"};
        Calculator calc = new Calculator();
        IOController controller = new IOController(new ConsoleOutput(), new StubInput(commands));
        int size = 6;
        InteractCalc interactCalc = new InteractCalc(controller, calc, size);

        interactCalc.start();
        double resultFromCalc = calc.getResult();

        Assert.assertEquals(result, resultFromCalc, 0.001);
    }

    @Test
    public void whenSubtractNumbersThenCalcHasResult() {
        double x = 6.0;
        double y = 2.0;
        double result = x - y;
        String[] commands = new String[] {"2", String.valueOf(x), String.valueOf(y), "5"};
        Calculator calc = new Calculator();
        IOController controller = new IOController(new ConsoleOutput(), new StubInput(commands));
        int size = 6;
        InteractCalc interactCalc = new InteractCalc(controller, calc, size);

        interactCalc.start();
        double resultFromCalc = calc.getResult();

        Assert.assertEquals(result, resultFromCalc, 0.001);
    }

    @Test
    public void whenMultiplyNumbersThenCalcHasResult() {
        double x = 6.0;
        double y = 2.0;
        double result = x * y;
        String[] commands = new String[] {"3", String.valueOf(x), String.valueOf(y), "5"};
        Calculator calc = new Calculator();
        IOController controller = new IOController(new ConsoleOutput(), new StubInput(commands));
        int size = 6;
        InteractCalc interactCalc = new InteractCalc(controller, calc, size);

        interactCalc.start();
        double resultFromCalc = calc.getResult();

        Assert.assertEquals(result, resultFromCalc, 0.001);
    }

    @Test
    public void whenDivideNumbersThenCalcHasResult() {
        double x = 6.0;
        double y = 2.0;
        double result = x / y;
        String[] commands = new String[] {"4", String.valueOf(x), String.valueOf(y), "5"};
        Calculator calc = new Calculator();
        IOController controller = new IOController(new ConsoleOutput(), new StubInput(commands));
        int size = 6;
        InteractCalc interactCalc = new InteractCalc(controller, calc, size);

        interactCalc.start();
        double resultFromCalc = calc.getResult();

        Assert.assertEquals(result, resultFromCalc, 0.001);
    }

}