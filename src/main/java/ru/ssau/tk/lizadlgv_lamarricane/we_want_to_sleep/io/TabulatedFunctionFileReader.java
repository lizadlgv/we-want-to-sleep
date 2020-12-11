package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.io;

import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.*;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.factory.*;

import java.io.*;

public class TabulatedFunctionFileReader {
    public static void main(String[] args) {
        File inputFile = new File("input/function.txt");
        try (BufferedReader readerArray = new BufferedReader(new FileReader(inputFile));
             BufferedReader readerLinked = new BufferedReader(new FileReader(inputFile))) {

            TabulatedFunction arrayFunction = FunctionsIO.readTabulatedFunction(readerArray, new ArrayTabulatedFunctionFactory());
            System.out.println(arrayFunction.toString());

            TabulatedFunction listFunction = FunctionsIO.readTabulatedFunction(readerLinked, new LinkedListTabulatedFunctionFactory());
            System.out.println(listFunction.toString());
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}