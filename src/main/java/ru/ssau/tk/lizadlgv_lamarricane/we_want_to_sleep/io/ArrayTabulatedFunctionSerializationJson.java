package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.io;

import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.ArrayTabulatedFunction;

import java.io.*;

import static ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.io.FunctionsIO.deserializeJson;

public class ArrayTabulatedFunctionSerializationJson {
    public static void main(String[] args) {
        File filePath = new File("output/serialized array functions.Json");
        double[] xValues = {0., 1., 2., 3.};
        double[] yValues = {0., 1., 4., 9.};

        ArrayTabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);

        try (BufferedWriter out = new BufferedWriter(new FileWriter(filePath))) {
            FunctionsIO.serializeJson(out, arrayFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader in = new BufferedReader(new FileReader(filePath))) {
            System.out.println(deserializeJson(in));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
