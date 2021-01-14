package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.*;

import static org.testng.Assert.assertEquals;

public class IntegratingMethodTest {
    private final double[] xValues = new double[]{1, 2, 3, 4, 5};
    private final double[] yValues = new double[]{2, 4, 6, 8, 10};
    private final MathFunction source1 = new SqrFunction();
    private final MathFunction source2 = new UnitFunction();

    private LinkedListTabulatedFunction getList() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    private ArrayTabulatedFunction getArray() {
        return new ArrayTabulatedFunction(xValues, yValues);
    }

    private ArrayTabulatedFunction getNiceTen() {
        int count = 256;
        double xFrom = 1;
        double xTo = 10;
        return new ArrayTabulatedFunction(source1, xFrom, xTo, count);
    }

    private ArrayTabulatedFunction getNiceSin() {
        int count = 128;
        double xFrom = 0;
        double xTo = 8;
        return new ArrayTabulatedFunction(source2, xFrom, xTo, count);
    }

    @Test
    public void testIntegrate() {
        IntegratingMethod sum = new IntegratingMethod();
        assertEquals(sum.integrate(getArray(), 1, 5), 23.6, 0.1);
        assertEquals(sum.integrate(getList(), 1, 5), 23.6, 0.1);
        assertEquals(sum.integrate(getNiceTen(), 1, 5), 40.5, 0.1);
        assertEquals(sum.integrate(getNiceSin(), 0, 2), 1.9, 0.1);
    }
}