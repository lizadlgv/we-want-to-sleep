package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

public class ConstantFunction implements MathFunction {
    private final double constant;

    public double getConstant() {
        return constant;
    }

    public ConstantFunction(double c) {
        this.constant = c;
    }

    @Override
    public double apply(double x) {
        return constant;
    }
}