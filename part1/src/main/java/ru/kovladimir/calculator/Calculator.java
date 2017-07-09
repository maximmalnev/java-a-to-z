package ru.kovladimir.calculator;

/**
 * Main logic of calculator.
 */
public class Calculator {
    /**
     * Result of action.
     */
	protected double result = 0;

    /**
     * Add two numbers and save result.
     * @param first double.
     * @param second double.
     */
	public void add(double first, double second) {
		result = first + second;
	}

    /**
     * Subtract two numbers and save result.
     * @param first double.
     * @param second double.
     */
	public void subtract(double first, double second) {
		result = first - second;
	}

    /**
     * Multiply two numbers and save result.
     * @param first double.
     * @param second double.
     */
	public void multiply(double first, double second) {
		result = first * second;
	}

    /**
     * Divide two numbers and save result.
     * @param first double.
     * @param second double.
     */
    public void divide(double first, double second) {
        result = first / second;
    }

    /**
     * Return the result.
     * @return double.
     */
	public double getResult() {
		return result;
	}
}