package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StrictTabulatedFunctionTest {
    private final static double DELTA = 0.0001;

    TabulatedFunction array = new StrictTabulatedFunction(
            new ArrayTabulatedFunction(new double[]{1., 2., 3.}, new double[]{1., 4., 9.}));

    TabulatedFunction list = new StrictTabulatedFunction(
            new LinkedListTabulatedFunction(new double[]{1., 2., 3., 4.}, new double[]{1., 4., 9., 16}));

    TabulatedFunction strictInUnmodifiable = new UnmodifiableTabulatedFunction(array);

    @Test
    public void testGetCount() {
        assertEquals(array.getCount(), 3);
        assertEquals(list.getCount(), 4);
        assertEquals(strictInUnmodifiable.getCount(), 3);
    }

    @Test
    public void testGetX() {
        assertEquals(array.getX(1), 2., DELTA);
        assertEquals(list.getX(3), 4., DELTA);
        assertEquals(strictInUnmodifiable.getX(0), 1., DELTA);
    }

    @Test
    public void testGetY() {
        assertEquals(array.getY(1), 4., DELTA);
        assertEquals(list.getY(3), 16., DELTA);
        assertEquals(strictInUnmodifiable.getY(0), 1., DELTA);
    }

    @Test
    public void testSetY() {
        TabulatedFunction array = new StrictTabulatedFunction(
                new ArrayTabulatedFunction(new double[]{1., 2.}, new double[]{1., 4.}));
        TabulatedFunction list = new StrictTabulatedFunction(
                new LinkedListTabulatedFunction(new double[]{1., 2., 3., 4.}, new double[]{1., 4., 9., 16}));
        array.setY(0, 2.);
        list.setY(3, 1.);
        assertEquals(array.getY(0), 2., DELTA);
        assertEquals(list.getY(3), 1., DELTA);
        assertThrows(UnsupportedOperationException.class, () -> strictInUnmodifiable.setY(1, 2.5));
    }

    @Test
    public void testIndexOfX() {
        assertEquals(array.indexOfX(3.), 2);
        assertEquals(list.indexOfX(1.), 0);
        assertEquals(strictInUnmodifiable.indexOfX(3.), 2);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(array.indexOfY(9.), 2);
        assertEquals(list.indexOfY(16.), 3);
        assertEquals(strictInUnmodifiable.indexOfY(1.), 0);
    }

    @Test
    public void testLeftBound() {
        assertEquals(array.leftBound(), 1., DELTA);
        assertEquals(list.leftBound(), 1., DELTA);
        assertEquals(strictInUnmodifiable.leftBound(), 1., DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(array.rightBound(), 3., DELTA);
        assertEquals(list.rightBound(), 4., DELTA);
        assertEquals(strictInUnmodifiable.rightBound(), 3., DELTA);
    }

    @Test
    public void testIterator() {
        int i = 0;
        for (Point point : list) {
            assertEquals(list.getX(i), point.x, DELTA);
            assertEquals(list.getY(i++), point.y, DELTA);
        }
        int j = 0;
        for (Point point : array) {
            assertEquals(array.getX(j), point.x, DELTA);
            assertEquals(array.getY(j++), point.y, DELTA);
        }
        int k = 0;
        for (Point point : strictInUnmodifiable) {
            assertEquals(strictInUnmodifiable.getX(k), point.x, DELTA);
            assertEquals(strictInUnmodifiable.getY(k++), point.y, DELTA);
        }
    }

    @Test
    public void testApply() {
        assertEquals(array.apply(3.), 9., DELTA);
        assertEquals(list.apply(4.), 16., DELTA);
        assertEquals(strictInUnmodifiable.apply(2.), 4., DELTA);
        assertThrows(UnsupportedOperationException.class, () -> array.apply(3.5));
        assertThrows(UnsupportedOperationException.class, () -> list.apply(-1.));
        assertThrows(UnsupportedOperationException.class, () -> strictInUnmodifiable.apply(1.5));
    }
}
