package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.factory;

import org.testng.annotations.Test;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.ArrayTabulatedFunction;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.StrictTabulatedFunction;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.TabulatedFunction;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.UnmodifiableTabulatedFunction;

import static org.testng.Assert.assertThrows;
import static org.testng.Assert.assertTrue;

public class ArrayTabulatedFunctionFactoryTest {
    private final double[] xValues = new double[]{1., 2., 3.};
    private final double[] yValues = new double[]{1., 4., 9.};
    private final TabulatedFunctionFactory array = new ArrayTabulatedFunctionFactory();

    @Test
    public void testCreate() {
        TabulatedFunction function = array.create(xValues, yValues);
        assertTrue(function instanceof ArrayTabulatedFunction);
    }

    @Test
    public void testCreateStrict() {
        TabulatedFunction function = array.createStrict(xValues, yValues);
        assertTrue(function instanceof StrictTabulatedFunction);
    }

    @Test
    public void testCreateStrictUnmodifiable() {
        TabulatedFunction function = array.createStrictUnmodifiable(xValues, yValues);
        assertTrue(function instanceof UnmodifiableTabulatedFunction);
        assertThrows(UnsupportedOperationException.class, () -> function.apply(0));
    }
}
