package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.exceptions.DifferentLengthOfArraysException;

import java.io.Serializable;

public abstract class AbstractTabulatedFunction implements TabulatedFunction, Serializable {
    private static final long serialVersionUID = -7987046689574387437L;
    protected int count;

    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return (leftY + (rightY - leftY) * (x - leftX) / (rightX - leftX));
    }

    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        } else if (x > rightBound()) {
            return extrapolateRight(x);
        } else if (indexOfX(x) != -1) {
            return getY(indexOfX(x));
        } else {
            return interpolate(x, floorIndexOfX(x));
        }
    }

    static protected void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException("Lengths of arrays are different");
        }
    }

    static protected void checkSorted(double[] xValues) {
        for (int i = 0; i < xValues.length - 1; i++) {
            if (xValues[i] >= xValues[i + 1]) {
                throw new ArrayIsNotSortedException("xValues array isn't sorted");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder representString = new StringBuilder();
        representString.append(this.getClass().getSimpleName()).append(" ").append("size = ").append(this.getCount());
        for (Point newPoint : this) {
            representString.append("\n").append("[").append(newPoint.x).append(";").append(" ").append(newPoint.y).append("]");
        }
        return representString.toString();
    }
}
