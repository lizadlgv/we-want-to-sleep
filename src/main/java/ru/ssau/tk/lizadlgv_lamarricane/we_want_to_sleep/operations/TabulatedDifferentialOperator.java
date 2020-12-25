package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.operations;

import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.concurrent.SynchronizedTabulatedFunction;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.*;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.factory.*;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {
    private TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] points = TabulatedFunctionOperationService.asPoints(function);
        double[] xValues = new double[points.length];
        double[] yValues = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            xValues[i] = points[i].x;
        }
        for (int i = 0; i < points.length - 1; i++) {
            yValues[i] = (points[i + 1].y - points[i].y) / (xValues[i + 1] - xValues[i]);
        }
        yValues[points.length - 1] = yValues[points.length - 2];
        return factory.create(xValues, yValues);
    }

    public TabulatedFunction deriveSynchronously(TabulatedFunction function) {
        Object mu = new Object();

        if (function instanceof SynchronizedTabulatedFunction) {
            return ((SynchronizedTabulatedFunction) function).doSynchronously(this::derive);
        }
        SynchronizedTabulatedFunction syncFunc = new SynchronizedTabulatedFunction(function, mu);
        return syncFunc.doSynchronously(this::derive);
    }
}
