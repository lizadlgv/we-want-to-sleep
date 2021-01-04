package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import org.jetbrains.annotations.NotNull;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.exceptions.InterpolationException;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Serializable, Insertable {
    private static final long serialVersionUID = 1242481747488848027L;
    private Node head;

    private static class Node implements Serializable {
        private static final long serialVersionUID = -915459550670427647L;
        public Node next;
        public Node prev;
        public double x;
        public double y;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2 || yValues.length < 2) {
            throw new IllegalArgumentException("Size of list is less than minimum (2)");
        }
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        for (int i = 0; i < xValues.length; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("Size of list is less than minimum (2)");
        }
        if (xFrom >= xTo) {
            throw new IllegalArgumentException("Max X is less, than min X");
        }
        double step = (xTo - xFrom) / (count - 1);
        if (xFrom < xTo) {
            for (int i = 0; i < count; i++) {
                addNode(xFrom, source.apply(xFrom));
                xFrom += step;
            }
        }
    }

    public void addNode(double x, double y) {
        Node newNode = new Node();
        newNode.x = x;
        newNode.y = y;
        if (head == null) {
            count = 0;
            head = newNode;
            newNode.prev = newNode;
            newNode.next = newNode;
        } else {
            Node last = head.prev;
            newNode.prev = last;
            newNode.next = head;
            last.next = newNode;
        }
        head.prev = newNode;
        count++;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
        Node someNode;
        if (index > (count / 2)) {
            someNode = head.prev;
            for (int i = count - 1; i > 0; i--) {
                if (i == index) {
                    break;
                } else {
                    someNode = someNode.prev;
                }
            }
        } else {
            someNode = head;
            for (int i = 0; i < count; i++) {
                if (i == index) {
                    break;
                } else {
                    someNode = someNode.next;
                }
            }
        }
        return someNode;
    }

    private Node floorNodeOfX(double x) {
        if (x < head.x) {
            throw new IllegalArgumentException("X is less than minimal value in linked list");
        }
        Node someNode = head;
        for (int i = 0; i < count; i++) {
            if (someNode.x <= x) {
                someNode = someNode.next;
            } else {
                return someNode.prev;
            }
        }
        return head.prev;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        getNode(index).y = value;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    @Override
    public int indexOfX(double x) {
        Node someX;
        someX = head;
        for (int i = 0; i < count; i++) {
            if (someX.x == x) {
                return i;
            } else {
                someX = someX.next;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node someY;
        someY = head;
        for (int i = 0; i < count; i++) {
            if (someY.y == y) {
                return i;
            } else {
                someY = someY.next;
            }
        }
        return -1;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < head.x) {
            throw new IllegalArgumentException("X is less than minimal value in linked list");
        }
        Node someNode = head;
        for (int i = 0; i < count; i++) {
            if (someNode.x <= x) {
                someNode = someNode.next;
            } else {
                return i - 1;
            }
        }
        return count - 1;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        Node left = getNode(floorIndex);
        Node right = left.next;
        if (x < left.x || right.x < x) {
            throw new InterpolationException("X is out of bounds of interpolation");
        }
        return interpolate(x, left.x, right.x, left.y, right.y);
    }

    protected double interpolate(double x, Node floorNode) {
        Node right = floorNode.next;
        if (x < floorNode.x || right.x < x) {
            throw new InterpolationException("X is out of bounds of interpolation");
        }
        return interpolate(x, floorNode.x, right.x, floorNode.y, right.y);
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        } else if (x > rightBound()) {
            return extrapolateRight(x);
        } else if (indexOfX(x) != -1) {
            return getY(indexOfX(x));
        } else {
            return interpolate(x, floorNodeOfX(x));
        }
    }

    @Override
    public void insert(double x, double y) {
        if (count == 0) {
            addNode(x, y);
        } else if (indexOfX(x) != -1) {
            setY(indexOfX(x), y);
        } else {
            int index = floorIndexOfX(x);
            Node newNode = new Node();
            newNode.x = x;
            newNode.y = y;

            if (index == 0) {
                newNode.next = head;
                newNode.prev = head.prev;
                head.prev.next = newNode;
                head = newNode;
            } else {
                if (index == count) {
                    newNode.next = head;
                    newNode.prev = head.prev;
                    head.prev.next = newNode;
                    head.prev = newNode;
                } else {
                    Node previous = getNode(index);
                    newNode.next = previous.next;
                    newNode.prev = previous;
                    previous.next = newNode;
                    newNode.next.prev = newNode;
                }
            }
            count++;
        }
    }

    @Override
    @NotNull
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private Node node = head;

            public boolean hasNext() {
                return (node != null);
            }

            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Point point = new Point(node.x, node.y);
                node = (node != head.prev) ? node.next : null;
                return point;
            }
        };
    }
}
