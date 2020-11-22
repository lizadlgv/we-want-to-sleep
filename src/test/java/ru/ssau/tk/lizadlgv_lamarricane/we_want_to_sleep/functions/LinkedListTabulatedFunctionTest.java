package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.exceptions.InterpolationException;

import java.util.Iterator;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {
    private final static double DELTA = 0.0001;

    private final double[] valuesX = new double[]{0., 1., 2., 3., 4.};
    private final double[] valuesY = new double[]{0., 1., 4., 9., 16.};

    private final MathFunction sqr = new SqrFunction();

    public LinkedListTabulatedFunction sampleFunction() {
        return new LinkedListTabulatedFunction(valuesX, valuesY);
    }

    public LinkedListTabulatedFunction sampleSqr() {
        return new LinkedListTabulatedFunction(sqr, 0., 4., 5);
    }

    SoftAssert softAssert = new SoftAssert();

    @Test
    public void testGetCount() {
        softAssert.assertEquals(sampleFunction().getCount(), 5, DELTA);
        softAssert.assertEquals(sampleSqr().getCount(), 5, DELTA);
        softAssert.assertAll();
    }

    @Test
    public void testLeftBound() {
        softAssert.assertEquals(sampleFunction().leftBound(), 0., DELTA);
        softAssert.assertEquals(sampleSqr().leftBound(), 0., DELTA);
        softAssert.assertAll();
    }

    @Test
    public void testRightBound() {
        softAssert.assertEquals(sampleFunction().rightBound(), 4., DELTA);
        softAssert.assertEquals(sampleSqr().rightBound(), 4., DELTA);
        softAssert.assertAll();
    }

    @Test
    public void testGetX() {
        softAssert.assertEquals(sampleFunction().getX(0), 0., DELTA);
        softAssert.assertEquals(sampleFunction().getX(2), 2., DELTA);
        softAssert.assertEquals(sampleFunction().getX(1), 1., DELTA);
        softAssert.assertEquals(sampleFunction().getX(4), 4., DELTA);
        softAssert.assertEquals(sampleSqr().getX(0), 0., DELTA);
        softAssert.assertEquals(sampleSqr().getX(2), 2., DELTA);
        softAssert.assertEquals(sampleSqr().getX(3), 3., DELTA);
        softAssert.assertEquals(sampleSqr().getX(4), 4., DELTA);
        assertThrows(IllegalArgumentException.class, () ->
                sampleFunction().getX(100));
        assertThrows(IllegalArgumentException.class, () ->
                sampleSqr().getX(-200));
        softAssert.assertAll();
    }

    @Test
    public void testGetY() {
        softAssert.assertEquals(sampleFunction().getY(0), 0., DELTA);
        softAssert.assertEquals(sampleFunction().getY(2), 4., DELTA);
        softAssert.assertEquals(sampleFunction().getY(1), 1., DELTA);
        softAssert.assertEquals(sampleFunction().getY(4), 16., DELTA);
        softAssert.assertEquals(sampleSqr().getY(0), 0., DELTA);
        softAssert.assertEquals(sampleSqr().getY(2), 4., DELTA);
        softAssert.assertEquals(sampleSqr().getY(3), 9., DELTA);
        softAssert.assertEquals(sampleSqr().getY(4), 16., DELTA);
        assertThrows(IllegalArgumentException.class, () ->
                sampleFunction().getY(50));
        assertThrows(IllegalArgumentException.class, () ->
                sampleSqr().getY(-1));
        softAssert.assertAll();
    }

    @Test
    public void testSetY() {
        LinkedListTabulatedFunction testSample = new LinkedListTabulatedFunction(valuesX, valuesY);
        LinkedListTabulatedFunction testSampleSqr = new LinkedListTabulatedFunction(sqr, 0., 4., 5);

        testSample.setY(1, 42.);
        testSampleSqr.setY(1, 42.);

        softAssert.assertEquals(testSample.getY(1), 42., DELTA);
        softAssert.assertEquals(testSampleSqr.getY(1), 42., DELTA);
        softAssert.assertAll();
        softAssert.assertAll();
    }

    @Test
    public void testIndexOfX() {
        softAssert.assertEquals(sampleFunction().indexOfX(4.), 4, DELTA);
        softAssert.assertEquals(sampleSqr().indexOfX(4.), 4, DELTA);
        softAssert.assertEquals(sampleFunction().indexOfX(42.), -1, DELTA);
        softAssert.assertEquals(sampleSqr().indexOfX(42.), -1, DELTA);
        softAssert.assertAll();
    }

    @Test
    public void testIndexOfY() {
        softAssert.assertEquals(sampleFunction().indexOfY(16.), 4, DELTA);
        softAssert.assertEquals(sampleSqr().indexOfY(16.), 4, DELTA);
        softAssert.assertEquals(sampleFunction().indexOfY(42.), -1, DELTA);
        softAssert.assertEquals(sampleSqr().indexOfY(42.), -1, DELTA);
        softAssert.assertAll();
    }

    @Test
    public void testFloorIndexOfX() {
        softAssert.assertEquals(sampleFunction().floorIndexOfX(3.5), 3, DELTA);
        softAssert.assertEquals(sampleSqr().floorIndexOfX(16.), 4, DELTA);
        softAssert.assertEquals(sampleSqr().floorIndexOfX(0), 0, DELTA);
        assertThrows(IllegalArgumentException.class, () ->
                sampleFunction().floorIndexOfX(-10));
        assertThrows(IllegalArgumentException.class, () ->
                sampleSqr().floorIndexOfX(-1));
        softAssert.assertAll();
    }

    @Test
    public void testInterpolate() {
        softAssert.assertEquals(sampleFunction().interpolate(2., 2), 4., DELTA);
        softAssert.assertEquals(sampleSqr().interpolate(2.5, 2), 6.5, DELTA);
        softAssert.assertEquals(sampleFunction().interpolate(0, 0), 0., DELTA);
        assertThrows(InterpolationException.class, () ->
                sampleFunction().interpolate(4., 4));
        assertThrows(InterpolationException.class, () ->
                sampleFunction().interpolate(2., 0));
        softAssert.assertAll();
    }

    @Test
    public void testExtrapolateLeft() {
        softAssert.assertEquals(sampleFunction().extrapolateLeft(-47.), -47., DELTA);
        softAssert.assertEquals(sampleSqr().extrapolateLeft(-1.), -1., DELTA);
        softAssert.assertAll();
    }

    @Test
    public void testExtrapolateRight() {
        softAssert.assertEquals(sampleFunction().extrapolateRight(5.), 23., DELTA);
        softAssert.assertEquals(sampleSqr().extrapolateRight(9.), 51., DELTA);
        softAssert.assertAll();
    }

    @Test
    public void testApply() {
        softAssert.assertEquals(sampleFunction().apply(-3.), -3., DELTA);
        softAssert.assertEquals(sampleFunction().apply(5.), 23., DELTA);
        softAssert.assertEquals(sampleFunction().apply(2.), 4., DELTA);
        softAssert.assertEquals(sampleFunction().apply(2.5), 6.5, DELTA);
        softAssert.assertEquals(sampleSqr().apply(-7.), -7., DELTA);
        softAssert.assertEquals(sampleSqr().apply(9.), 51., DELTA);
        softAssert.assertEquals(sampleSqr().apply(4.), 16., DELTA);
        softAssert.assertEquals(sampleSqr().apply(1.2), 1.6, DELTA);
        softAssert.assertAll();
    }

    @Test
    public void testComplexFunction() {
        final double[] valuesX = new double[]{0., 1., 2., 3., 4., 5., 6.};
        final double[] valuesY = new double[]{0., 1., 4., 9., 16., 25., 36.};
        TabulatedFunction f = new ArrayTabulatedFunction(valuesX, valuesY);
        TabulatedFunction g = new LinkedListTabulatedFunction(valuesX, valuesY);
        MathFunction h = new SqrFunction();

        softAssert.assertEquals(f.andThen(g).andThen(h).apply(-2.), 4., DELTA);
        softAssert.assertEquals(f.andThen(g).andThen(h).apply(8.), 369664., DELTA);
        softAssert.assertEquals(f.andThen(g).andThen(h).apply(2.), 256., DELTA);
        softAssert.assertEquals(f.andThen(g).andThen(h).apply(2.5), 1722.25, DELTA);
        softAssert.assertAll();
    }

    @Test
    public void testIterator() {
        LinkedListTabulatedFunction testSample = sampleFunction();
        Iterator<Point> iterator = testSample.iterator();
        int i = 0;
        int j = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            softAssert.assertEquals(testSample.getX(i), point.x, DELTA);
            softAssert.assertEquals(testSample.getY(i++), point.y, DELTA);
        }
        for (Point point : testSample) {
            softAssert.assertEquals(testSample.getX(j), point.x, DELTA);
            softAssert.assertEquals(testSample.getY(j++), point.y, DELTA);
        }
        softAssert.assertAll();
    }
}
