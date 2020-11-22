package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

public interface MathFunction {
    double apply(double x);

    default CompositeFunction andThen(MathFunction afterFunction) {
        return new CompositeFunction(this, afterFunction);
    }
}
