package ru.kovladimir.calculator;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class IOControllerTest {

    @Test
    public void whenShowMessageThenOutputIt() {
        String data = "Test";
        String separator = System.lineSeparator();
        String result = data + separator;
        PrintStream defaultOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        IOController controller = new IOController(new ConsoleOutput(), new ConsoleInput());

        controller.showMessage(data);
        System.setOut(defaultOut);

        Assert.assertEquals(result, outContent.toString());
    }

    @Test
    public void whenAskIntWithValidationThenReturnRightKey() {
        int key = 1;
        int[] range = new int[]{key, 2, 3};
        String[] commands = new String[]{"test", String.valueOf(4), String.valueOf(1)};
        IOController controller = new IOController(new ConsoleOutput(), new StubInput(commands));

        int result = controller.askIntWithValidation(range);

        Assert.assertEquals(result, key);
    }

    @Test
    public void whenAskDoubleThenReturnRightKey() {
        double key = 1.0;
        double oldValue = 2.0;
        String[] commands = new String[]{"test", String.valueOf(key)};
        IOController controller = new IOController(new ConsoleOutput(), new StubInput(commands));

        double result = controller.askDouble(oldValue);

        Assert.assertEquals(result, key, 0.001);
    }

    @Test
    public void whenAskDoubleAndEnterOldValueThenReturnIt() {
        double oldValue = 2.0;
        String[] commands = new String[]{"test", "old value"};
        IOController controller = new IOController(new ConsoleOutput(), new StubInput(commands));

        double result = controller.askDouble(oldValue);

        Assert.assertEquals(result, oldValue, 0.001);
    }
}
