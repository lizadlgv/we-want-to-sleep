package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class ZeroFunctionTest {
    private final ZeroFunction testFunction = new ZeroFunction();

    @Test
    public void testApply() {
        assertEquals(testFunction.getConstant(), 0, 0.1);
    }
}
