package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import org.testng.annotations.Test;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.exceptions.DifferentLengthOfArraysException;

import static org.testng.Assert.*;

public class AbstractTabulatedFunctionTest {
    private final static double DELTA = 0.0001;

    public MockTabulatedFunction mockFunction = new MockTabulatedFunction();

    @Test
    public void testInterpolate() {
        assertEquals(mockFunction.interpolate(3, 6, 9, 7, 3), 11, DELTA);
    }

    @Test
    public void testApply() {
        assertEquals(mockFunction.apply(7), 6, DELTA);
        assertEquals(mockFunction.apply(-7), -8, DELTA);
        assertEquals(mockFunction.apply(2), 1, DELTA);
        assertNotEquals(mockFunction.apply(1), 5, DELTA);
    }

    @Test
    public void testCheckLengthIsTheSame() {
        assertThrows(DifferentLengthOfArraysException.class, () -> {
            double[] valuesX = new double[]{-3, 5};
            double[] valuesY = new double[]{9};
            AbstractTabulatedFunction.checkLengthIsTheSame(valuesX, valuesY);
        });
    }

    @Test
    public void testCheckSorted() {
        assertThrows(ArrayIsNotSortedException.class, () -> {
            double[] valuesX = new double[]{-3, 5, 7, 9, 0};
            AbstractTabulatedFunction.checkSorted(valuesX);
        });
    }
}