package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.concurrent;

import org.testng.annotations.Test;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.*;

import static org.testng.Assert.*;

public class SynchronizedTabulatedFunctionTest {
    private final double[] xValues = new double[]{1, 2, 3, 4, 5, 6, 7};
    private final double[] yValues = new double[]{10, 20, 30, 40, 50, 60, 70};
    private final Object object = new Object();
    private final double DELTA = 0.00001;

    private SynchronizedTabulatedFunction getSynchronizedList() {
        return new SynchronizedTabulatedFunction(new LinkedListTabulatedFunction(xValues, yValues), object);
    }

    private SynchronizedTabulatedFunction getSynchronizedArray() {
        return new SynchronizedTabulatedFunction(new ArrayTabulatedFunction(xValues, yValues), object);
    }

    SynchronizedTabulatedFunction synchronizedList = getSynchronizedList();
    SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();

    @Test
    public void testGetCount() {
        assertEquals(synchronizedList.getCount(), 7, DELTA);
        assertEquals(synchronizedArr.getCount(), 7, DELTA);
    }

    @Test
    public void testGetX() {
        assertEquals(synchronizedList.getX(0), 1, DELTA);
        assertEquals(synchronizedList.getX(3), 4, DELTA);
        assertEquals(synchronizedList.getX(5), 6, DELTA);

        assertEquals(synchronizedArr.getX(1), 2, DELTA);
        assertEquals(synchronizedArr.getX(4), 5, DELTA);
        assertEquals(synchronizedArr.getX(6), 7, DELTA);
    }

    @Test
    public void testGetY() {
        assertEquals(synchronizedList.getY(0), 10, DELTA);
        assertEquals(synchronizedList.getY(3), 40, DELTA);
        assertEquals(synchronizedList.getY(5), 60, DELTA);

        assertEquals(synchronizedArr.getY(1), 20, DELTA);
        assertEquals(synchronizedArr.getY(4), 50, DELTA);
        assertEquals(synchronizedArr.getY(6), 70, DELTA);
    }

    @Test
    public void testSetY() {
        synchronizedList.setY(0, 11);
        assertEquals(synchronizedList.getY(0), 11, DELTA);
        synchronizedList.setY(3, 44);
        assertEquals(synchronizedList.getY(3), 44, DELTA);
        synchronizedList.setY(6, 77);
        assertEquals(synchronizedList.getY(6), 77, DELTA);

        synchronizedArr.setY(2, 33);
        assertEquals(synchronizedArr.getY(2), 33, DELTA);
        synchronizedArr.setY(4, 55);
        assertEquals(synchronizedArr.getY(4), 55, DELTA);
        synchronizedArr.setY(5, 66);
        assertEquals(synchronizedArr.getY(5), 66, DELTA);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(synchronizedList.indexOfX(1), 0, DELTA);
        assertEquals(synchronizedList.indexOfX(3), 2, DELTA);
        assertEquals(synchronizedList.indexOfX(6), 5, DELTA);

        assertEquals(synchronizedArr.indexOfX(2), 1, DELTA);
        assertEquals(synchronizedArr.indexOfX(4), 3, DELTA);
        assertEquals(synchronizedArr.indexOfX(7), 6, DELTA);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(synchronizedList.indexOfY(10), 0, DELTA);
        assertEquals(synchronizedList.indexOfY(30), 2, DELTA);
        assertEquals(synchronizedList.indexOfY(60), 5, DELTA);

        assertEquals(synchronizedArr.indexOfY(20), 1, DELTA);
        assertEquals(synchronizedArr.indexOfY(40), 3, DELTA);
        assertEquals(synchronizedArr.indexOfY(70), 6, DELTA);
    }

    @Test
    public void testLeftBound() {
        assertEquals(synchronizedList.leftBound(), 1, DELTA);
        assertEquals(synchronizedArr.leftBound(), 1, DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(synchronizedList.rightBound(), 7, DELTA);
        assertEquals(synchronizedArr.rightBound(), 7, DELTA);
    }

    @Test
    public void testApply() {
        assertEquals(synchronizedList.apply(3), 30, DELTA);
        assertEquals(synchronizedList.apply(-7), -70, DELTA);
        assertEquals(synchronizedList.apply(0), 0, DELTA);
        assertEquals(synchronizedArr.apply(0), 0, DELTA);
        assertEquals(synchronizedArr.apply(8), 80, DELTA);
        assertEquals(synchronizedArr.apply(-4), -40, DELTA);
    }

    @Test
    public void testDoSynchronously() {
        assertEquals((int) synchronizedList.doSynchronously(SynchronizedTabulatedFunction::getCount), 7);
        assertEquals((double) synchronizedList.doSynchronously(SynchronizedTabulatedFunction::rightBound), 7);
    }

    @Test
    public void testIterator() {
        int i = 0;
        int j = 0;
        for (Point point : synchronizedList) {
            assertEquals(point.x, synchronizedList.getX(i++));
            assertEquals(point.y, synchronizedList.getY(j++));
        }
        assertEquals(i, synchronizedList.getCount(), DELTA);
        assertEquals(j, synchronizedList.getCount(), DELTA);

        i = 0;
        j = 0;
        for (Point point : synchronizedArr) {
            assertEquals(point.x, synchronizedArr.getX(i++));
            assertEquals(point.y, synchronizedArr.getY(j++));
        }
        assertEquals(i, synchronizedArr.getCount(), DELTA);
        assertEquals(j, synchronizedArr.getCount(), DELTA);
    }
}