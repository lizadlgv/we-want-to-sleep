package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import org.testng.annotations.Test;

import org.testng.asserts.SoftAssert;

public class LinkedListTabulatedFunctionTest {

    private final static double DELTA = 0.0001;

    private final double[] valuesX = new double[]{0., 1., 2., 3., 4.};
    private final double[] valuesY = new double[]{0., 1., 4., 9., 16.};

    private final MathFunction sqr = new SqrFunction();
    private final MathFunction division = new DivisionFunction();

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
        softAssert.assertEquals(sampleFunction().floorIndexOfX(-1.), 0, DELTA);
        softAssert.assertEquals(sampleSqr().floorIndexOfX(0), 0, DELTA);
        softAssert.assertAll();
    }

    @Test
    public void testInterpolate() {
        softAssert.assertEquals(sampleFunction().interpolate(2., 2), 4., DELTA);
        softAssert.assertEquals(sampleSqr().interpolate(2.5, 4), 10., DELTA);
        softAssert.assertEquals(sampleFunction().interpolate(0, 0), 0., DELTA);
        softAssert.assertEquals(sampleSqr().interpolate(4., 2), 14., DELTA);
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
        softAssert.assertEquals(sampleFunction().andThen(sqr).apply(-3.), 9., DELTA);
        softAssert.assertEquals(sampleFunction().andThen(sqr).apply(5.), 529., DELTA);
        softAssert.assertEquals(sampleFunction().andThen(sqr).apply(2.), 16., DELTA);
        softAssert.assertEquals(sampleFunction().andThen(sqr).apply(2.5), 42.25, DELTA);
        softAssert.assertEquals(sampleSqr().andThen(division).apply(-7.), -3.5, DELTA);
        softAssert.assertEquals(sampleSqr().andThen(division).apply(9.), 25.5, DELTA);
        softAssert.assertEquals(sampleSqr().andThen(division).apply(4.), 8., DELTA);
        softAssert.assertEquals(sampleSqr().andThen(division).apply(1.2), 0.8, DELTA);
        softAssert.assertAll();
    }
}