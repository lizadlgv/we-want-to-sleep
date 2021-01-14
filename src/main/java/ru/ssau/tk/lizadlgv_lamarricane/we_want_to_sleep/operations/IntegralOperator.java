package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.operations;

import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.MathFunction;

public interface IntegralOperator<T extends MathFunction> {
    double integrate(T function, double xFrom, double xTo);
}
