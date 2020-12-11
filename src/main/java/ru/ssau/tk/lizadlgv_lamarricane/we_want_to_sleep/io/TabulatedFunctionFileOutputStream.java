package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.io;

import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.ArrayTabulatedFunction;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.LinkedListTabulatedFunction;

import java.io.*;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {
        double[] xValues = {1, 3, 5};
        double[] yValues = {1, 3, 5};
        ArrayTabulatedFunction arrayFunc = new ArrayTabulatedFunction(xValues, yValues);
        LinkedListTabulatedFunction linkFunc = new LinkedListTabulatedFunction(xValues, yValues);
        try (BufferedOutputStream outputStreamArr = new BufferedOutputStream(new FileOutputStream("output/array function.bin"));
             BufferedOutputStream outputStreamLink = new BufferedOutputStream(new FileOutputStream("output/linked list function.bin"))) {
            FunctionsIO.writeTabulatedFunction(outputStreamArr, arrayFunc);
            FunctionsIO.writeTabulatedFunction(outputStreamLink, linkFunc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
