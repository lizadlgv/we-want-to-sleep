package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConstantFunctionTest {
    private final ConstantFunction testFunction = new ConstantFunction(7);

    @Test
    public void testApply() {
        assertEquals(testFunction.apply(1), 7.0, 0.1);
        assertEquals(testFunction.apply(-7.3), 7.0, 0.1);
        assertEquals(testFunction.apply(12), 7.0, 0.1);
    }
}
