package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.io;

import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.*;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.factory.*;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.operations.*;

import java.io.*;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("input/binary function.bin"))) {
            TabulatedFunction arrayFunc = FunctionsIO.readTabulatedFunction(inputStream, new ArrayTabulatedFunctionFactory());
            System.out.println(arrayFunc.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Введите размер и значения функции:");
            System.out.println(new TabulatedDifferentialOperator().derive(FunctionsIO.readTabulatedFunction(new BufferedReader(new InputStreamReader(System.in)), new LinkedListTabulatedFunctionFactory())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
