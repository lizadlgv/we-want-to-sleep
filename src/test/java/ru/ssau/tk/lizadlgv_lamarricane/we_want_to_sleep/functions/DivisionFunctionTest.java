package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DivisionFunctionTest {
    private final DivisionFunction testDivision = new DivisionFunction();

    @Test
    public void testApply() {
        assertEquals(testDivision.apply(10), 5, 0.1);
        assertEquals(testDivision.apply(-4), -2, 0.1);
        assertEquals(testDivision.apply(1), 0.5, 0.1);
        assertEquals(testDivision.apply(0), 0, 0.1);
    }
}