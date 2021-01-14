package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.operations;

import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.TabulatedFunction;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class IntegratingTask extends RecursiveAction {
    private final TabulatedFunction function;
    private final double xFrom;
    private final double xTo;
    private static final double threshold = 1;
    private final List<Double> result;

    public IntegratingTask(TabulatedFunction function, double xFrom, double xTo, List<Double> result) {
        this.function = function;
        this.xFrom = xFrom;
        this.xTo = xTo;
        this.result = result;
    }

    @Override
    protected void compute() {
        double length = xTo - xFrom;
        if (length <= threshold) {
            result.add(computeDirectly());
            return;
        }
        double split = length / 2;
        invokeAll(new IntegratingTask(function, xFrom, xFrom + split, result), new IntegratingTask(function, xFrom + split, xTo, result));

    }

    private Double computeDirectly() {
        IntegratingMethod integral = new IntegratingMethod();
        return integral.integrate(function, xFrom, xTo);
    }
}
