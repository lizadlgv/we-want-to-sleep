package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.factory;

import org.testng.annotations.Test;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.StrictTabulatedFunction;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.TabulatedFunction;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.UnmodifiableTabulatedFunction;

import static org.testng.Assert.assertThrows;
import static org.testng.Assert.assertTrue;

public class LinkedListTabulatedFunctionFactoryTest {
    private final double[] xValues = new double[]{1., 2., 3.};
    private final double[] yValues = new double[]{1., 4., 9.};
    private final TabulatedFunctionFactory linkedList = new LinkedListTabulatedFunctionFactory();

    @Test
    public void testCreate() {
        TabulatedFunction function = linkedList.create(xValues, yValues);
        assertTrue(function instanceof LinkedListTabulatedFunction);
    }

    @Test
    public void testCreateStrict() {
        TabulatedFunction function = linkedList.createStrict(xValues, yValues);
        assertTrue(function instanceof StrictTabulatedFunction);
    }

    @Test
    public void createUnmodifiable() {
        TabulatedFunction function = linkedList.createUnmodifiable(xValues, yValues);
        assertTrue(function instanceof UnmodifiableTabulatedFunction);
    }

    @Test
    public void testCreateStrictUnmodifiable() {
        TabulatedFunction function = linkedList.createStrictUnmodifiable(xValues, yValues);
        assertThrows(UnsupportedOperationException.class, () -> function.setY(0, 0));
        assertThrows(UnsupportedOperationException.class, () -> function.apply(0));
    }
}
