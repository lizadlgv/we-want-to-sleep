package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.operations;

import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.MathFunction;

public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction> {
    protected double step;

    public SteppingDifferentialOperator(double step) {
        this.step = step;
        if (step <= 0 || step == Double.POSITIVE_INFINITY || Double.isNaN(step)) {
            throw new IllegalArgumentException("Step does not match");
        }
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }
}
