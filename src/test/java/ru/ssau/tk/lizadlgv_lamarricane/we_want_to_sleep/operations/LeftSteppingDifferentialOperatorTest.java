package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.SqrFunction;

import static org.testng.Assert.assertEquals;

public class LeftSteppingDifferentialOperatorTest {
    private final static double DELTA = 0.000001;

    @Test
    public void testDerive() {
        double step = 0.001;

        LeftSteppingDifferentialOperator differentialOperator = new LeftSteppingDifferentialOperator(step);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 1.999, DELTA);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(2), 3.999, DELTA);
    }
}