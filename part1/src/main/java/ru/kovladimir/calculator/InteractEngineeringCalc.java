package ru.kovladimir.calculator;


public class InteractEngineeringCalc extends InteractCalc {

    /**
     * New calculator.
     */
    protected EngineeringCalculator calculator;

    /**
     * Default constructor.
     *
     * @param inOutController IOController.
     * @param calculator      Calculator.
     * @param size int.
     */
    public InteractEngineeringCalc(IOController inOutController, EngineeringCalculator calculator, int size) {
        super(inOutController, calculator, size);
        this.calculator = calculator;
    }

    /**
     * Add new actions.
     */
    @Override
    protected void fillActions() {
        super.fillActions();
        actions[6] = getNewSinAction();
        actions[7] = getNewCosAction();
    }

    /**
     * Get new action to find sin of the angle.
     *
     * @return BaseAction.
     */
    private BaseAction getNewSinAction() {
        return new BaseAction("Sinus.") {
            @Override
            int getKey() {
                return 6;
            }

            @Override
            void execute() {
                inOutController.showMessage("Please, enter the angle in degrees or enter \"old value\" to use old result:");
                double angle = inOutController.askDouble(calculator.getResult());
                calculator.sin(Math.toRadians(angle));
            }
        };
    }

    /**
     * Get new action to find cos of the angle.
     *
     * @return BaseAction.
     */
    private BaseAction getNewCosAction() {
        return new BaseAction("Cosinus.") {
            @Override
            int getKey() {
                return 7;
            }

            @Override
            void execute() {
                inOutController.showMessage("Please, enter the angle in degrees or enter \"old value\" to use old result:");
                double angle = inOutController.askDouble(calculator.getResult());
                calculator.cos(Math.toRadians(angle));
            }
        };
    }
}
