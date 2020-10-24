package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

public class MockTabulatedFunction extends AbstractTabulatedFunction {
    final double x0 = 2.0;
    final double x1 = 5.0;
    final double y0 = 1.0;
    final double y1 = 4.0;

    @Override
    protected int floorIndexOfX(double x) {
        if (x < x0) {
            return 0;
        } else if (x >= x0 && x <= x1) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        return super.interpolate(x, x0, x1, y0, y1);
    }


    public double apply(double x) {
        return super.apply(x);

    }


    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return super.interpolate(x, leftX, rightX, leftY, rightY);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public double getX(int index) {
        return 0;
    }

    @Override
    public double getY(int index) {
        if (index == 0) {
            return y0;
        }
        return y1;
    }

    @Override
    public void setY(int index, double value) {

    }

    @Override
    public int indexOfX(double x) {
        if (x == x0) {
            return 0;
        }
        if (x == x1) {
            return 1;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        return 0;
    }

    @Override
    public double leftBound() {
        return x0;
    }

    @Override
    public double rightBound() {
        return x1;
    }
}