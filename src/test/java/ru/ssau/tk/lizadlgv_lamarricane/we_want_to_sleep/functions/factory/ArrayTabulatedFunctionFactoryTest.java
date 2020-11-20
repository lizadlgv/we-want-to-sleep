package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.factory;

import org.testng.annotations.Test;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.ArrayTabulatedFunction;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.TabulatedFunction;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionFactoryTest {
    private final double[] xValues = new double[]{1., 2., 3.};
    private final double[] yValues = new double[]{1., 4., 9.};

    @Test
    public void testCreate() {
        TabulatedFunctionFactory array = new ArrayTabulatedFunctionFactory();
        TabulatedFunction function = array.create(xValues, yValues);
        assertTrue(function instanceof ArrayTabulatedFunction);
    }
}