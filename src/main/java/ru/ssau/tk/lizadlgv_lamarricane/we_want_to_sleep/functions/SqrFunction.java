package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import java.lang.Math;

public class SqrFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return x*x;
    }
}