package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.concurrent;

import org.jetbrains.annotations.NotNull;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.*;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.operations.TabulatedFunctionOperationService;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SynchronizedTabulatedFunction implements TabulatedFunction {
    private final TabulatedFunction tabulatedFunction;
    private final Object object;

    public SynchronizedTabulatedFunction(TabulatedFunction tabulatedFunction, Object object) {
        this.tabulatedFunction = tabulatedFunction;
        this.object = Objects.requireNonNull(object);
    }

    public interface Operation<T> {
        T apply(SynchronizedTabulatedFunction synchronizedTabulatedFunction);
    }

    public <T> T doSynchronously(Operation<? extends T> operation) {
        synchronized (object) {
            return operation.apply(this);
        }
    }

    @Override
    public int getCount() {
        synchronized (object) {
            return tabulatedFunction.getCount();
        }
    }

    @Override
    public double getX(int index) {
        synchronized (object) {
            return tabulatedFunction.getX(index);
        }
    }

    @Override
    public double getY(int index) {
        synchronized (object) {
            return tabulatedFunction.getY(index);
        }
    }

    @Override
    public void setY(int index, double value) {
        synchronized (object) {
            tabulatedFunction.setY(index, value);
        }
    }

    @Override
    public int indexOfX(double x) {
        synchronized (object) {
            return tabulatedFunction.indexOfX(x);
        }
    }

    @Override
    public int indexOfY(double y) {
        synchronized (object) {
            return tabulatedFunction.indexOfY(y);
        }
    }

    @Override
    public double leftBound() {
        synchronized (object) {
            return tabulatedFunction.leftBound();
        }
    }

    @Override
    public double rightBound() {
        synchronized (object) {
            return tabulatedFunction.rightBound();
        }
    }

    @Override
    public double apply(double x) {
        synchronized (object) {
            return tabulatedFunction.apply(x);
        }
    }

    @NotNull
    @Override
    public Iterator<Point> iterator() {
        synchronized (tabulatedFunction) {
            Point[] points = TabulatedFunctionOperationService.asPoints(tabulatedFunction);
            return new Iterator<Point>() {
                int i = 0;

                @Override
                public boolean hasNext() {
                    return i != points.length;
                }

                @Override
                public Point next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    } else {
                        return new Point(points[i].x, points[i++].y);
                    }
                }
            };
        }
    }
}