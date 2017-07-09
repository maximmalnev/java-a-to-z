package ru.kovladimir.calculator;

/**
 * I/O Controller to interact with user.
 */
public class IOController {

    /**
     * Output.
     */
    private Output output;

    /**
     * Input.
     */
    private Input input;

    /**
     * Default constructor.
     * @param output Output.
     * @param input Input.
     */
    public IOController(Output output, Input input) {
        this.output = output;
        this.input = input;
    }

    /**
     * Show message to user by using output.
     * @param message String.
     */
    public void showMessage(String message) {
        output.displayMessage(message);
    }

    /**
     * Ask user to enter int value within the range.
     * @param range int[]
     * @return key.
     */
    public int askIntWithValidation(int[] range) {
        int key = askInt();
        if (!isIntFromRange(key, range)) {
            output.displayMessage("This value is not in the range. Please try again:");
            key = askIntWithValidation(range);
        }
        return key;
    }

    /**
     * Ask double value from user. If user enter "old value" return oldValue;
     * @param oldValue double.
     * @return key.
     */
    public double askDouble(double oldValue) {
        double key;
        String lineFromUser = input.askString();
        if (lineFromUser.equalsIgnoreCase("old value")) {
            key = oldValue;
        } else {
            try {
                key = Double.parseDouble(lineFromUser);
            } catch (NumberFormatException e) {
                output.displayMessage("This value is not numeral. Please try again:");
                key = askDouble(oldValue);
            }
        }
        return key;
    }

    /**
     * Ask int value from user.
     * @return key.
     */
    private int askInt() {
        int key;
        String lineFromUser = input.askString();
        try {
            key = Integer.parseInt(lineFromUser);
        } catch (NumberFormatException e) {
            output.displayMessage("This value is not numeral. Please try again:");
            key = askInt();
        }
        return key;
    }

    /**
     * Check if int value is within the range.
     * @param key int.
     * @param range int[].
     * @return isFromRange.
     */
    private boolean isIntFromRange(int key, int[] range) {
        boolean isFromRange = false;
        for (int valueFromRange : range) {
            if (key == valueFromRange) {
                isFromRange = true;
                break;
            }
        }
        return isFromRange;
    }
}
