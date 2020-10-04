package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

public class FourthRootFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return Math.pow(x, 1.0 / 4.0);
    }
}
