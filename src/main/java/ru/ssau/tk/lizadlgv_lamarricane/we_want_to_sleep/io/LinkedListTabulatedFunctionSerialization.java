package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.io;

import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.*;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.operations.TabulatedDifferentialOperator;

import java.io.*;

import static ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.io.FunctionsIO.*;

public class LinkedListTabulatedFunctionSerialization {
    public static void main(String[] args) {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("output/serialized linked list functions.bin"))) {
            LinkedListTabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(new SqrFunction(), 0, 10, 20);
            TabulatedFunction first = new TabulatedDifferentialOperator().derive(tabulatedFunction);
            TabulatedFunction second = new TabulatedDifferentialOperator().derive(first);
            serialize(outputStream, tabulatedFunction);
            serialize(outputStream, first);
            serialize(outputStream, second);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("output/serialized linked list functions.bin"))) {
            System.out.println(deserialize(inputStream).toString() + '\n' + deserialize(inputStream).toString() + '\n' + deserialize(inputStream));
        } catch (IOException | ClassNotFoundException ioe) {
            ioe.printStackTrace();
        }
    }
}
