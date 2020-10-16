package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
    private Node head;
    private final Node last = head.prev;

    private void addNode(double x, double y) {
        Node newNode = new Node();

        if (head == null) {
            head = newNode;
            newNode.next = newNode;
            newNode.prev = newNode;
            newNode.x = x;
            newNode.y = y;
        } else {
            last.next = newNode;
            head.prev = newNode;
            newNode.prev = last;
            newNode.next = head;
            newNode.x = x;
            newNode.y = y;
        }
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        for (int i = 0; i < xValues.length; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        double step = (xTo - xFrom) / (count - 1);
        if (xFrom < xTo) {
            for (int i = 0; i < count; i++) {
                addNode(xFrom, source.apply(xFrom));
                xFrom += step;
            }
        }
    }

    private Node getNode(int index) {
        Node someNode;

        if (index > (count / 2)) {
            someNode = last;
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

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return last.x;
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
            return 0;
        }
        Node someNode = head;
        for (int i = 0; i <= count; i++) {
            if (someNode.x < x) {
                someNode = someNode.next;
            } else {
                return i - 1;
            }
        }
        return getCount();
    }

    @Override
    protected double extrapolateLeft(double x) {
        if (head.x == last.x) {
            return x;
        }
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        if (head.x == last.x) {
            return x;
        }
        return interpolate(x, last.prev.x, last.x, last.prev.y, last.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (head.x == last.x) {
            return x;
        }

        Node left = getNode(floorIndex);
        Node right = left.next;
        return interpolate(x, left.x, right.x, left.y, right.y);
    }
}

