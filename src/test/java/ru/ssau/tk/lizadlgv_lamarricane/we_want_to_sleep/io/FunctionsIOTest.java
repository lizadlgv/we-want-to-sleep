package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.io;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.*;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.factory.ArrayTabulatedFunctionFactory;

import java.io.*;

import static org.testng.Assert.*;

public class FunctionsIOTest {
    double[] xValues = new double[]{1., 2., 3., 4., 5.};
    double[] yValues = new double[]{2., 4., 6., 8., 10.};
    private final TabulatedFunction list = new LinkedListTabulatedFunction(xValues, yValues);

    @Test
    public void testReadWriteTabulatedFunction() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("temp/first function.txt"))) {
            FunctionsIO.writeTabulatedFunction(writer, list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("temp/first function.txt"))) {
            TabulatedFunction arrayTest = FunctionsIO.readTabulatedFunction(reader, new ArrayTabulatedFunctionFactory());

            assertEquals(list.getCount(), arrayTest.getCount());

            for (int i = 0; i < list.getCount(); i++) {
                assertEquals(list.getX(i), arrayTest.getX(i));
                assertEquals(list.getY(i), arrayTest.getY(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInputOutputTabulatedFunction() {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("temp/second function.bin"))) {
            FunctionsIO.writeTabulatedFunction(out, list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("temp/second function.bin"))) {
            TabulatedFunction arrayTest = FunctionsIO.readTabulatedFunction(in, new ArrayTabulatedFunctionFactory());

            assertEquals(list.getCount(), arrayTest.getCount());

            for (int i = 0; i < list.getCount(); i++) {
                assertEquals(list.getX(i), arrayTest.getX(i));
                assertEquals(list.getY(i), arrayTest.getY(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSerializeDeserializeTabulatedFunction() {
        LinkedListTabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(new SqrFunction(), 0, 10, 10);

        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("temp/serialized linked list functions.bin"))) {
            FunctionsIO.serialize(outputStream, tabulatedFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("temp/serialized linked list functions.bin"))) {
            TabulatedFunction resultArray = FunctionsIO.deserialize(inputStream);

            assertEquals(tabulatedFunction.getCount(), resultArray.getCount());

            for (int i = 0; i < tabulatedFunction.getCount(); i++) {
                assertEquals(tabulatedFunction.getX(i), resultArray.getX(i));
                assertEquals(tabulatedFunction.getY(i), resultArray.getY(i));
            }
        } catch (IOException | ClassNotFoundException ioe) {
            ioe.printStackTrace();
        }
    }

    @Test
    public void testJson() {
        ArrayTabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);

        try (BufferedWriter out = new BufferedWriter(new FileWriter("temp/serialized array functions.Json"))) {
            FunctionsIO.serializeJson(out, arrayFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader in = new BufferedReader(new FileReader("temp/serialized array functions.Json"))) {
            TabulatedFunction resultArray = FunctionsIO.deserializeJson(in);

            assertEquals(arrayFunction.getCount(), resultArray.getCount());

            for (int i = 0; i < arrayFunction.getCount(); i++) {
                assertEquals(arrayFunction.getX(i), resultArray.getX(i));
                assertEquals(arrayFunction.getY(i), resultArray.getY(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testXML() {
        ArrayTabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);

        try (BufferedWriter out = new BufferedWriter(new FileWriter("temp/serialized array functions.XML"))) {
            FunctionsIO.serializeXml(out, arrayFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader in = new BufferedReader(new FileReader("temp/serialized array functions.XML"))) {
            TabulatedFunction resultArray = FunctionsIO.deserializeXml(in);

            assertEquals(arrayFunction.getCount(), resultArray.getCount());

            for (int i = 0; i < arrayFunction.getCount(); i++) {
                assertEquals(arrayFunction.getX(i), resultArray.getX(i));
                assertEquals(arrayFunction.getY(i), resultArray.getY(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void deleteOnExit() {
        for (File myFile : new File("temp").listFiles())
            if (myFile.isFile() && myFile.delete()) {
                System.out.println(myFile.getName() + " deleted");
            }
    }
}