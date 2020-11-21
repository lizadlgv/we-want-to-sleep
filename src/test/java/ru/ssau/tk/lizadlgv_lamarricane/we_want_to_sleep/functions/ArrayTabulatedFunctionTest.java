package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.Iterator;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {
    private final static double DELTA = 0.0001;
    private final double[] valuesX = new double[]{-3., -2., -1, 0., 1., 2., 3., 4., 5.};
    private final double[] valuesY = new double[]{-13., -4., -1., 0., 1., 4., 9., 13., 25.};
    private final MathFunction sqrFunc = new SqrFunction();

    private ArrayTabulatedFunction getDefinedThroughArrays() {
        return new ArrayTabulatedFunction(valuesX, valuesY);
    }

    private ArrayTabulatedFunction getDefinedThroughMathFunction() {
        return new ArrayTabulatedFunction(sqrFunc, 0, 9, 109);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(getDefinedThroughArrays().floorIndexOfX(13), 9, DELTA);
        assertEquals(getDefinedThroughArrays().floorIndexOfX(-1), 2, DELTA);
        assertEquals(getDefinedThroughMathFunction().floorIndexOfX(666), 109, DELTA);
        assertThrows(IllegalArgumentException.class, () ->
                getDefinedThroughArrays().floorIndexOfX(-10));
        assertThrows(IllegalArgumentException.class, () ->
                getDefinedThroughMathFunction().floorIndexOfX(-1));
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(getDefinedThroughArrays().extrapolateLeft(-13), -103, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().extrapolateLeft(-1), -2, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(getDefinedThroughArrays().extrapolateRight(99), 1153, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().extrapolateRight(-66), 3, DELTA);
    }

    @Test
    public void testInterpolate() {
        assertEquals(getDefinedThroughArrays().interpolate(0.666, getDefinedThroughArrays().floorIndexOfX(0.666)), 0.666, DELTA);
        assertEquals(getDefinedThroughMathFunction().interpolate(7.25, getDefinedThroughMathFunction().floorIndexOfX(7.25)), 52.5625, DELTA);
    }

    @Test
    public void testGetCount() {
        assertEquals(getDefinedThroughArrays().getCount(), 9);
        assertEquals(getDefinedThroughMathFunction().getCount(), 109);
    }

    @Test
    public void testGetX() {
        assertEquals(getDefinedThroughArrays().getX(0), -3, DELTA);
        assertEquals(getDefinedThroughArrays().getX(6), 3, DELTA);
        assertEquals(getDefinedThroughArrays().getX(8), 5, DELTA);
        assertEquals(getDefinedThroughMathFunction().getX(0), 0, DELTA);
        assertEquals(getDefinedThroughMathFunction().getX(35), 2.916667, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().getX(81), 27, DELTA);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getDefinedThroughArrays().getX(-1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getDefinedThroughMathFunction().getX(100000));
    }

    @Test
    public void testGetY() {
        assertEquals(getDefinedThroughArrays().getY(0), -13, DELTA);
        assertEquals(getDefinedThroughArrays().getY(8), 25, DELTA);
        assertNotEquals(getDefinedThroughArrays().getY(7), 2, DELTA);
        assertEquals(getDefinedThroughMathFunction().getY(0), 0, DELTA);
        assertEquals(getDefinedThroughMathFunction().getY(20), 2.7777776, DELTA);
        assertEquals(getDefinedThroughMathFunction().getY(108), 81, DELTA);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getDefinedThroughArrays().getY(-1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getDefinedThroughMathFunction().getY(100000));
    }

    @Test
    public void testSetY() {
        ArrayTabulatedFunction testDefinedThroughArrays = getDefinedThroughArrays();
        ArrayTabulatedFunction testDefinedThroughMathFunction = getDefinedThroughMathFunction();

        testDefinedThroughArrays.setY(1, 4);
        testDefinedThroughMathFunction.setY(0, 1009);
        assertEquals(testDefinedThroughArrays.getY(1), 4, DELTA);
        assertEquals(testDefinedThroughMathFunction.getY(0), 1009, DELTA);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getDefinedThroughArrays().setY(9, 100500));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getDefinedThroughMathFunction().setY(-1, 100500));
    }

    @Test
    public void testIndexOfX() {
        assertEquals(getDefinedThroughArrays().indexOfX(3), 6, DELTA);
        assertEquals(getDefinedThroughArrays().indexOfX(0.65), -1, DELTA);
        assertEquals(getDefinedThroughMathFunction().indexOfX(77), -1, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().indexOfX(0.1), 0, DELTA);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(getDefinedThroughArrays().indexOfY(26), -1, DELTA);
        assertEquals(getDefinedThroughArrays().indexOfY(0.11), -1, DELTA);
        assertEquals(getDefinedThroughMathFunction().indexOfY(81), -1, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().indexOfY(0.1), 100, DELTA);
    }

    @Test
    public void testLeftBound() {
        assertEquals(getDefinedThroughArrays().leftBound(), -3, DELTA);
        assertEquals(getDefinedThroughMathFunction().leftBound(), 0, DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(getDefinedThroughArrays().rightBound(), 5, DELTA);
        assertEquals(getDefinedThroughMathFunction().rightBound(), 9, DELTA);
    }

    @Test
    public void testApply() {
        assertEquals(getDefinedThroughArrays().apply(-4), -22, DELTA);
        assertEquals(getDefinedThroughArrays().apply(13), 121, DELTA);
        assertEquals(getDefinedThroughArrays().apply(-3), -13, DELTA);
        assertEquals(getDefinedThroughArrays().apply(5), 25, DELTA);
        assertEquals(getDefinedThroughArrays().apply(1.3), 1.9, DELTA);

        assertEquals(getDefinedThroughMathFunction().apply(-4), -0.33333, DELTA);
        assertEquals(getDefinedThroughMathFunction().apply(-3), -0.25, DELTA);
        assertEquals(getDefinedThroughMathFunction().apply(5), 25, DELTA);
        assertEquals(getDefinedThroughMathFunction().apply(1.3), 1.691667, DELTA);
        assertEquals(getDefinedThroughMathFunction().apply(13), 152.66667, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().apply(100), 10, DELTA);
    }

    @Test
    public void testComplexFunctions() {
        double xFrom = 5;
        double xTo = 10;
        int count = 64;
        MathFunction sqr = new SqrFunction();
        MathFunction div = new DivisionFunction();
        MathFunction unit = new UnitFunction();
        ArrayTabulatedFunction f = new ArrayTabulatedFunction(div.andThen(sqr).andThen(unit), xFrom, xTo, count);
        LinkedListTabulatedFunction g = new LinkedListTabulatedFunction(div.andThen(sqr).andThen(unit), xFrom, xTo, count);
        assertEquals(f.getY(0), g.getY(0), 0.001);
        assertEquals(f.getY(1), g.getY(1), 0.001);
        assertEquals(f.getY(2), g.getY(2), 0.001);
    }

    @Test
    public void testIteratorCycleWhile() {
        Iterator<Point> iterator = getDefinedThroughArrays().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(getDefinedThroughArrays().getX(i), point.x, DELTA);
            assertEquals(getDefinedThroughArrays().getY(i++), point.y, DELTA);
        }
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testIteratorCycleForEach() {
        int i = 0;
        for (Point point : getDefinedThroughArrays()) {
            assertEquals(getDefinedThroughArrays().getX(i), point.x, DELTA);
            assertEquals(getDefinedThroughArrays().getY(i++), point.y, DELTA);
        }
    }
}