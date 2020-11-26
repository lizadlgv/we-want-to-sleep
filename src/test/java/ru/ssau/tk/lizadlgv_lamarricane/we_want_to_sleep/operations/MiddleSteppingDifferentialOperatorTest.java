package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.operations;

import org.testng.annotations.Test;

import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.SqrFunction;

import static org.testng.Assert.*;

public class MiddleSteppingDifferentialOperatorTest {
    private final static double DELTA = 0.000001;

    @Test
    public void testDerive() {
        double step = 0.001;

        MiddleSteppingDifferentialOperator differentialOperator = new MiddleSteppingDifferentialOperator(step);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 2, DELTA);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(2), 4, DELTA);
    }
}