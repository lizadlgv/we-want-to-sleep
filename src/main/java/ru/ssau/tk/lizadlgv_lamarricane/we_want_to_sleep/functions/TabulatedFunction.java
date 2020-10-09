package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

public interface TabulatedFunction extends MathFunction {
    int getCount(); //метод получения количества табулированных значений

    double getX(int index); //метод, получающий значение аргумента x по номеру индекса

    double getY(int index); //метод, получающий значение аргумента x по номеру индекса

    void setY(int index, double value); //метод, получающий значение аргумента x по номеру индекса

    int indexOfX(double x); //метод, возвращающий индекс аргумента x

    int indexOfY(double y); //метод, возвращающий индекс первого вхождения значения y

    double leftBound(); //метод, возвращающий самый левый x

    double rightBound(); //метод, возвращающий самый правый x
}
