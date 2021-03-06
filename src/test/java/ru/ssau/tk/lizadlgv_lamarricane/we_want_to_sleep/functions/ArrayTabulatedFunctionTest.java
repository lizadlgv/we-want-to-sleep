package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import org.testng.annotations.Test;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.exceptions.InterpolationException;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
    public void testConstructorException() {
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(new double[6], new double[1]));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(new double[1], new double[2]));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(new double[]{6.}, new double[]{}));

        assertThrows(DifferentLengthOfArraysException.class, () -> new ArrayTabulatedFunction(new double[6], new double[3]));
        assertThrows(DifferentLengthOfArraysException.class, () -> new ArrayTabulatedFunction(new double[7], new double[9]));
        assertThrows(DifferentLengthOfArraysException.class, () -> new ArrayTabulatedFunction(new double[]{10., 11., 2.}, new double[]{9., 1.}));

        assertThrows(ArrayIsNotSortedException.class, () -> new ArrayTabulatedFunction(new double[]{2., -1., 0}, new double[]{3., 4., 5.}));
        assertThrows(ArrayIsNotSortedException.class, () -> new ArrayTabulatedFunction(new double[]{30., -50., 90., 100.}, new double[]{1., 10., 20., 30.}));
        assertThrows(ArrayIsNotSortedException.class, () -> new ArrayTabulatedFunction(new double[]{1., 2., 4., 3.}, new double[]{1., 2., 3., 4.}));

        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(sqrFunc, 1., 5., 1));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(sqrFunc, 4., 3., 2));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(sqrFunc, 5., 5., 10));
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
        assertThrows(InterpolationException.class, () ->
                getDefinedThroughArrays().interpolate(1.5, 2));
        assertThrows(InterpolationException.class, () ->
                getDefinedThroughMathFunction().interpolate(-4., 0));
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
        assertEquals(i, 9);
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testIteratorCycleForEach() {
        int i = 0;
        for (Point point : getDefinedThroughArrays()) {
            assertEquals(getDefinedThroughArrays().getX(i), point.x, DELTA);
            assertEquals(getDefinedThroughArrays().getY(i++), point.y, DELTA);
        }
        assertEquals(i, 9);
    }

    @Test
    public void testInsert() {
        double[] valuesX = new double[]{0., 1., 2., 3., 4., 5., 6., 7., 8., 9., 10.};
        double[] valuesY = new double[]{0., 2., 4., 6., 8., 10., 12., 14., 16., 18., 20.};
        ArrayTabulatedFunction testDefinedThroughArrays = new ArrayTabulatedFunction(valuesX, valuesY);

        for (int i = 0; i < testDefinedThroughArrays.getCount(); i++) {
            assertEquals(testDefinedThroughArrays.getX(i), i, DELTA);
            assertEquals(testDefinedThroughArrays.getY(i), 2 * i, DELTA);
        }
        testDefinedThroughArrays.insert(-1., -2.);
        testDefinedThroughArrays.insert(11., 22.);
        for (int i = 0; i < testDefinedThroughArrays.getCount(); i++) {
            assertEquals(testDefinedThroughArrays.getX(i), i - 1, DELTA);
            assertEquals(testDefinedThroughArrays.getY(i), 2 * (i - 1), DELTA);
        }

        testDefinedThroughArrays.insert(-1., 3.);
        assertEquals(testDefinedThroughArrays.getX(0), -1., DELTA);
        assertEquals(testDefinedThroughArrays.getY(0), 3., DELTA);
        testDefinedThroughArrays.insert(5.5, 11.);
        assertEquals(testDefinedThroughArrays.getX(7), 5.5, DELTA);
        assertEquals(testDefinedThroughArrays.getY(7), 11., DELTA);
        testDefinedThroughArrays.insert(6., 14.);
        assertEquals(testDefinedThroughArrays.getX(8), 6., DELTA);
        assertEquals(testDefinedThroughArrays.getY(8), 14., DELTA);

        assertEquals(testDefinedThroughArrays.getCount(), 14);
    }

    @Test
    public void testRemove() {
        ArrayTabulatedFunction testDefinedThroughArrays = getDefinedThroughArrays();
        testDefinedThroughArrays.remove(0);
        testDefinedThroughArrays.remove(6);
        testDefinedThroughArrays.remove(5);
        /* old array: [(-3, -13) (-2, -4) (-1, -1) (0, 0) (1, 1) (2, 4) (3, 9) (4, 13) (5, 25)]
        array: [(-2, -4) (-1, -1) (0, 0) (1, 1) (2, 4) (4, 13)] */
        assertEquals(testDefinedThroughArrays.getX(0), -2., DELTA);
        assertEquals(testDefinedThroughArrays.getX(1), -1., DELTA);
        assertEquals(testDefinedThroughArrays.getX(2), 0., DELTA);
        assertEquals(testDefinedThroughArrays.getX(3), 1., DELTA);

        assertThrows(IllegalArgumentException.class, () -> {
            double[] valuesXTest = new double[]{-13., 21.};
            double[] valuesYTest = new double[]{19., 44.};
            ArrayTabulatedFunction testRemove = new ArrayTabulatedFunction(valuesXTest, valuesYTest);
            testRemove.remove(0);
        });
    }
}
