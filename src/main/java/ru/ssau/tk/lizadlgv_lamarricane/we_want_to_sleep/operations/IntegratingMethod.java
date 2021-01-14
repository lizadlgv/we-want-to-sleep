package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.operations;

import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.TabulatedFunction;

public class IntegratingMethod implements IntegralOperator<TabulatedFunction> {
    @Override
    public double integrate(TabulatedFunction function, double xFrom, double xTo) {
        double ACCURACY = 0.0000001;
        if (xFrom < function.leftBound() - ACCURACY) {
            throw new IllegalArgumentException("Incorrect low bound of integrating");
        } else if (xTo > function.rightBound() + ACCURACY) {
            throw new IllegalArgumentException("Incorrect high bounds of integrating");
        } else if (xFrom >= xTo) {
            throw new IllegalArgumentException("Incorrect bounds: xFrom >= xTo");
        }

        double n = Byte.MAX_VALUE;
        double h = (xTo - xFrom) / (n - 1);
        double xEven;
        double xOdd;
        double integral = function.apply(xFrom) + function.apply(xTo);

        for (int j = 1; j < n / 2 - 1; j++) {
            xEven = xFrom + h * 2 * j;
            xOdd = xFrom + h * (2 * j - 1);
            integral += 2 * function.apply(xEven) + 4 * function.apply(xOdd);
        }
        integral += function.apply(xFrom + (n - 1) * h);
        return integral * h / 3;
    }
}
