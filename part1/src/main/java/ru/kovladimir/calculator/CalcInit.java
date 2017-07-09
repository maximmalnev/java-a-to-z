package ru.kovladimir.calculator;


/**
 * Initialize calculator and IOController.
 */
public class CalcInit {
	public static void main(String[] args) {
        EngineeringCalculator calc = new EngineeringCalculator();
        IOController controller = new IOController(new ConsoleOutput(), new ConsoleInput());
        int size = 8;
        InteractEngineeringCalc interactCalc = new InteractEngineeringCalc(controller, calc, size);
        interactCalc.start();
    }
}