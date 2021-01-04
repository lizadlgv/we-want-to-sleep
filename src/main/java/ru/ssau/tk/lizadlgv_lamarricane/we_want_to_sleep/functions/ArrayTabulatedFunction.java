package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.exceptions.InterpolationException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Serializable, Insertable, Removable {
    private static final long serialVersionUID = 925973407340487180L;
    private double[] xValues;
    private double[] yValues;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2 || yValues.length < 2) {
            throw new IllegalArgumentException("Size of list is less than minimum (2)");
        }
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        count = xValues.length;
        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues, count);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("Size of list is less than minimum (2)");
        }
        if (xFrom >= xTo) {
            throw new IllegalArgumentException("Max X is less, than min X");
        }
        this.count = count;
        xValues = new double[count];
        yValues = new double[count];
        double step = (xTo - xFrom) / (count - 1);
        double xMomentValue = xFrom;

        for (int i = 0; i < count; i++) {
            xValues[i] = xMomentValue;
            yValues[i] = source.apply(xMomentValue);
            xMomentValue += step;
        }
    }

    @Override
    public int getCount() {
        return (count);
    }

    @Override
    public double getX(int index) {
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        yValues[index] = value;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < count; i++) {
            if (xValues[i] == x) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; i++) {
            if (yValues[i] == y) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < xValues[0]) {
            throw new IllegalArgumentException("X is less than the left border of tabulated function");
        }
        for (int i = 0; i + 1 < count; i++) {
            if (xValues[i + 1] > x) {
                return i;
            }
        }
        return count;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, xValues[0], xValues[1], yValues[0], yValues[1]);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, xValues[count - 2], xValues[count - 1], yValues[count - 2], yValues[count - 1]);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (x < xValues[floorIndex] || xValues[floorIndex + 1] < x) {
            throw new InterpolationException("X is out of bounds of interpolation");
        }
        return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex], yValues[floorIndex + 1]);
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < count;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Point point = new Point(xValues[i], yValues[i]);
                i++;
                return point;
            }
        };
    }

    @Override
    public void insert(double x, double y) {
        if (indexOfX(x) != -1) {
            setY(indexOfX(x), y);
        } else {
            double[] xNewValues = new double[count + 1];
            double[] yNewValues = new double[count + 1];
            if (x < leftBound()) {
                xNewValues[0] = x;
                yNewValues[0] = y;
                System.arraycopy(xValues, 0, xNewValues, 1, count);
                System.arraycopy(yValues, 0, yNewValues, 1, count);
            } else {
                if (x > rightBound()) {
                    System.arraycopy(xValues, 0, xNewValues, 0, count);
                    System.arraycopy(yValues, 0, yNewValues, 0, count);
                    xNewValues[count] = x;
                    yNewValues[count] = y;
                } else {
                    int i = floorIndexOfX(x);
                    System.arraycopy(xValues, 0, xNewValues, 0, i + 1);
                    System.arraycopy(yValues, 0, yNewValues, 0, i + 1);
                    xNewValues[i + 1] = x;
                    yNewValues[i + 1] = y;
                    System.arraycopy(xValues, i + 1, xNewValues, i + 2, count - i - 1);
                    System.arraycopy(yValues, i + 1, yNewValues, i + 2, count - i - 1);
                }
            }
            this.xValues = xNewValues;
            this.yValues = yNewValues;
            count++;
        }
    }

    @Override
    public void remove(int index) {
        if (count <= 2) {
            throw new IllegalArgumentException("Less than minimum length");
        }
        double[] xTempValues = new double[count - 1];
        double[] yTempValues = new double[count - 1];

        System.arraycopy(xValues, 0, xTempValues, 0, index);
        System.arraycopy(yValues, 0, yTempValues, 0, index);
        System.arraycopy(xValues, index + 1, xTempValues, index, count - index - 1);
        System.arraycopy(yValues, index + 1, yTempValues, index, count - index - 1);
        this.xValues = xTempValues;
        this.yValues = yTempValues;
        count--;
    }
}
