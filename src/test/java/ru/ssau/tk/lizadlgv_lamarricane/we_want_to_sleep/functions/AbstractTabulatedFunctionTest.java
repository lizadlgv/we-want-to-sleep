package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import org.testng.annotations.Test;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.exceptions.DifferentLengthOfArraysException;

import static org.testng.Assert.*;

public class AbstractTabulatedFunctionTest {
    private final static double DELTA = 0.0001;
    private final double[] valuesX1 = new double[]{-15, -3, -1, 0, 1, 3, 15};
    private final double[] valuesY1 = new double[]{-5, -2, -1, -0, 1, 2};

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
        assertThrows(DifferentLengthOfArraysException.class, () -> AbstractTabulatedFunction.checkLengthIsTheSame(valuesX1, valuesY1));
        double[] valuesX = new double[]{-1, 5};
        double[] valuesY = new double[]{9, 2};
        AbstractTabulatedFunction.checkLengthIsTheSame(valuesX, valuesY);
    }

    @Test
    public void testCheckSorted() {
        assertThrows(ArrayIsNotSortedException.class, () -> {
            double[] valuesX = new double[]{-3, 5, 7, 9, 0};
            AbstractTabulatedFunction.checkSorted(valuesX);
        });
        AbstractTabulatedFunction.checkSorted(valuesX1);
    }

    @Test
    public void testToString(){
        double[] xValues = {0.0, 0.5, 1.0};
        double[] yValues = {0.0, 0.25, 1.0};
        ArrayTabulatedFunction arrayFunc = new ArrayTabulatedFunction(xValues, yValues);
        LinkedListTabulatedFunction linkFunc = new LinkedListTabulatedFunction(xValues, yValues);
        String stringOfLink = "LinkedListTabulatedFunction size = 3\n[0.0; 0.0]\n[0.5; 0.25]\n[1.0; 1.0]";
        String stringOfArr = "ArrayTabulatedFunction size = 3\n[0.0; 0.0]\n[0.5; 0.25]\n[1.0; 1.0]";
        assertEquals(stringOfArr, arrayFunc.toString());
        assertEquals(stringOfLink, linkFunc.toString());
    }
}
