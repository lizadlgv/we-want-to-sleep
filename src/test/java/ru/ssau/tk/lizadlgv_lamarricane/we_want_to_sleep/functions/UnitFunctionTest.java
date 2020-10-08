package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class UnitFunctionTest {
    private final UnitFunction testFunction = new UnitFunction();

    @Test
    public void testApply() {
        assertEquals(testFunction.getConstant(), 1.0);
    }
}
