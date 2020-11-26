package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.ArrayTabulatedFunction;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.Point;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.TabulatedFunction;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.factory.LinkedListTabulatedFunctionFactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TabulatedDifferentialOperatorTest {
    private static final double DELTA = 0.0001;

    private static final TabulatedDifferentialOperator defaultOperator = new TabulatedDifferentialOperator();
    private static final TabulatedDifferentialOperator arrayOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
    private static final TabulatedDifferentialOperator listOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());

    @Test
    public void testGetFactory() {
        assertTrue(defaultOperator.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(arrayOperator.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(listOperator.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSetFactory() {
        defaultOperator.setFactory(new LinkedListTabulatedFunctionFactory());
        arrayOperator.setFactory(new LinkedListTabulatedFunctionFactory());
        listOperator.setFactory(new ArrayTabulatedFunctionFactory());
        assertTrue(defaultOperator.getFactory() instanceof LinkedListTabulatedFunctionFactory);
        assertTrue(arrayOperator.getFactory() instanceof LinkedListTabulatedFunctionFactory);
        assertTrue(listOperator.getFactory() instanceof ArrayTabulatedFunctionFactory);
    }

    @Test
    public void testDerive() {
        double[] xValues = new double[]{1., 2., 3., 4.};
        double[] yValues = new double[]{1., 4., 9., 16.};
        double[] derivedYValues = new double[]{3., 5., 7., 7.};

        TabulatedFunction derivedDefaultFunction = new TabulatedDifferentialOperator().derive(
                (new ArrayTabulatedFunctionFactory().create(xValues, yValues)));
        assertTrue(derivedDefaultFunction instanceof ArrayTabulatedFunction);
        int i = 0;
        for (Point point : derivedDefaultFunction) {
            assertEquals(point.y, derivedYValues[i++], DELTA);
        }

        TabulatedFunction derivedArrayFunction = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory()).derive(
                (new ArrayTabulatedFunctionFactory().create(xValues, yValues)));
        assertTrue(derivedArrayFunction instanceof ArrayTabulatedFunction);
        int j = 0;
        for (Point point : derivedArrayFunction) {
            assertEquals(point.y, derivedYValues[j++], DELTA);
        }

        TabulatedFunction derivedListFunction = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory()).derive(
                (new ArrayTabulatedFunctionFactory().create(xValues, yValues)));
        assertTrue(derivedListFunction instanceof LinkedListTabulatedFunction);
        int k = 0;
        for (Point point : derivedListFunction) {
            assertEquals(point.y, derivedYValues[k++], DELTA);
        }
    }
}
