package ru.kovladimir.calculator;

/**
 * Ingeneering Calculator. Based on Calculator.
 */
public class EngineeringCalculator extends Calculator {

    /**
     * Finder sin of the angle. Angle defined in radians.
     * @param angleInRad double.
     */
    public void sin(double angleInRad) {
        result = Math.sin(angleInRad);
    }

    /**
     * Finder cos of the angle. Angle defined in radians.
     * @param angleInRad double.
     */
    public void cos(double angleInRad) {
        result = Math.cos(angleInRad);
    }
}
