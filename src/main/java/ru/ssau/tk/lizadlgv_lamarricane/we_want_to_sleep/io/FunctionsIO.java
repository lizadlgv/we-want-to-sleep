package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.io;

import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.Point;
import ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions.TabulatedFunction;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class FunctionsIO {
    private FunctionsIO() {
        throw new UnsupportedOperationException();
    }

    static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        DataOutputStream out = new DataOutputStream(outputStream);
        out.writeInt(function.getCount());
        for (Point newPoint : function) {
            out.writeDouble(newPoint.x);
            out.writeDouble(newPoint.y);
        }
        out.flush();
    }
}