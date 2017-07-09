package ru.kovladimir.calculator;

/**
 * Class can interact with the user.
 */
public class InteractCalc {
    /**
     * Calculator. Main logic to find result.
     */
    protected Calculator calculator;

    /**
     * Input/Output Controller to interact with user.
     */
    protected IOController inOutController;

    /**
     * Array of actions.
     */
    protected BaseAction[] actions;

    /**
     * Exit of program.
     */
    private boolean exit = false;

    /**
     * Default constructor.
     *
     * @param inOutController IOController.
     * @param calculator      Calculator.
     * @param size            int.
     */
    public InteractCalc(IOController inOutController, Calculator calculator, int size) {
        this.inOutController = inOutController;
        this.calculator = calculator;
        actions = new BaseAction[size];
    }

    /**
     * Add all actions to array.
     */
    protected void fillActions() {
        actions[0] = getNewShowResultAction();
        actions[1] = getNewAdditionAction();
        actions[2] = getNewSubtractionAction();
        actions[3] = getNewMultiplicationAction();
        actions[4] = getNewDivisionAction();
        actions[5] = getNewQuitAction();
    }

    /**
     * Main loop of the program.
     */
    public void start() {
        fillActions();
        while (!exit) {
            showMenu();
            inOutController.showMessage("Select action:");
            int key = inOutController.askIntWithValidation(getKeys());
            executeAction(key);
        }
    }

    /**
     * Print all actions..
     */
    private void showMenu() {
        for (BaseAction action : actions) {
            showAction(action);
        }
    }

    /**
     * Print one action.
     *
     * @param action BaseAction.
     */
    public void showAction(BaseAction action) {
        inOutController.showMessage(action.info());
    }

    /**
     * Execute action by key.
     *
     * @param key int.
     */
    private void executeAction(int key) {
        actions[key].execute();
    }

    /**
     * Get keys of all actions.
     *
     * @return int[].
     */
    private int[] getKeys() {
        int[] keys = new int[actions.length];
        for (int i = 0; i < keys.length; i++) {
            keys[i] = actions[i].getKey();
        }
        return keys;
    }

    /**
     * Get new action to show result of the calculation.
     *
     * @return BaseAction.
     */
    private BaseAction getNewShowResultAction() {

        /**
         * Show result action.
         */
        return new BaseAction("Show result.") {

            /**
             * {@inheritDoc}
             */
            @Override
            int getKey() {
                return 0;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            void execute() {
                inOutController.showMessage(String.valueOf(calculator.getResult()));
            }
        };
    }

    /**
     * Get new action to makeMove two numbers.
     *
     * @return BaseAction.
     */
    private BaseAction getNewAdditionAction() {

        /**
         * Add action.
         */
        return new BaseAction("Add.") {

            /**
             * {@inheritDoc}
             */
            @Override
            int getKey() {
                return 1;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            void execute() {
                inOutController.showMessage("Please, enter first addend or enter \"old value\" to use old result:");
                double firstAddend = inOutController.askDouble(calculator.getResult());
                inOutController.showMessage("Please, enter second addend or enter \"old value\" to use old result:");
                double secondAddend = inOutController.askDouble(calculator.getResult());
                calculator.add(firstAddend, secondAddend);
            }
        };
    }

    /**
     * Get new action to subtract two numbers.
     *
     * @return BaseAction.
     */
    private BaseAction getNewSubtractionAction() {

        /**
         * Subtract action.
         */
        return new BaseAction("Subtract.") {

            /**
             * {@inheritDoc}
             */
            @Override
            int getKey() {
                return 2;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            void execute() {
                inOutController.showMessage("Please, enter minuend: or enter \"old value\" to use old result");
                double minuend = inOutController.askDouble(calculator.getResult());
                inOutController.showMessage("Please, enter subtrahend or enter \"old value\" to use old result:");
                double subtrahend = inOutController.askDouble(calculator.getResult());
                calculator.subtract(minuend, subtrahend);
            }
        };
    }

    /**
     * Get new action to multiply two numbers.
     *
     * @return BaseAction.
     */
    private BaseAction getNewMultiplicationAction() {

        /**
         * Multiply action.
         */
        return new BaseAction("Multiplication.") {

            /**
             * {@inheritDoc}
             */
            @Override
            int getKey() {
                return 3;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            void execute() {
                inOutController.showMessage("Please, enter first factor or enter \"old value\" to use old result:");
                double firstFactor = inOutController.askDouble(calculator.getResult());
                inOutController.showMessage("Please, enter second factor or enter \"old value\" to use old result:");
                double secondFactor = inOutController.askDouble(calculator.getResult());
                calculator.multiply(firstFactor, secondFactor);
            }
        };
    }

    /**
     * Get new action to divide two numbers.
     *
     * @return BaseAction.
     */
    private BaseAction getNewDivisionAction() {

        /**
         * Divide action.
         */
        return new BaseAction("Division.") {

            /**
             * {@inheritDoc}
             */
            @Override
            int getKey() {
                return 4;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            void execute() {
                inOutController.showMessage("Please, enter dividend or enter \"old value\" to use old result:");
                double dividend = inOutController.askDouble(calculator.getResult());
                inOutController.showMessage("Please, enter divider or enter \"old value\" to use old result:");
                double divider = inOutController.askDouble(calculator.getResult());
                calculator.divide(dividend, divider);
            }
        };
    }

    /**
     * Get new action to quit the program..
     *
     * @return BaseAction.
     */
    private BaseAction getNewQuitAction() {

        /**
         * Quit action.
         */
        return new BaseAction("Quit.") {

            /**
             * {@inheritDoc}
             */
            @Override
            int getKey() {
                return 5;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            void execute() {
                InteractCalc.this.exit = true;
            }
        };
    }

    /**
     * Class that describe action.
     */
    protected abstract class BaseAction {

        /**
         * Description of the action.
         */
        String description;

        /**
         * Default constructor.
         *
         * @param description String.
         */
        BaseAction(String description) {
            this.description = description;
        }

        /**
         * Get key of the action.
         *
         * @return int.
         */
        abstract int getKey();

        /**
         * Main logic of the action.
         */
        abstract void execute();

        /**
         * Get info about action.
         *
         * @return String.
         */
        String info() {
            return String.format("%d. %s", this.getKey(), this.description);
        }
    }
}