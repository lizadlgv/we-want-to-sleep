package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.factory;

import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);
}