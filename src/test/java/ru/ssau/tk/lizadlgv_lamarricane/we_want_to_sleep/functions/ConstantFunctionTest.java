package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class ConstantFunctionTest {
    @Test
    public void testApply() {
        ConstantFunction testFunction = new ConstantFunction(7);
        assertEquals(testFunction.apply(1), 7.0);
        assertEquals(testFunction.apply(-7.3), 7.0);
        assertEquals(testFunction.apply(12), 7.0);
    }
}