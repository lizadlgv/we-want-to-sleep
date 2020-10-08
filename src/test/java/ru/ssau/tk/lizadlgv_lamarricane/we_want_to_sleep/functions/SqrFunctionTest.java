package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrFunctionTest {
    private final SqrFunction testFunction = new SqrFunction();

    @Test
    public void testApply() {
        assertEquals(testFunction.apply(5), 25);
        assertEquals(testFunction.apply(-11), 121);
        assertEquals(testFunction.apply(-1), 1);
        assertEquals(testFunction.apply(0.1), 0.01, 0.01);
    }
}