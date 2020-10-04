package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class DivisionFunctionTest {

    @Test
    public void testApply() {
        DivisionFunction testDivision = new DivisionFunction();
        assertEquals(testDivision.apply(10), 5);
        assertEquals(testDivision.apply(-4), -2);
        assertEquals(testDivision.apply(1), 0.5);
        assertEquals(testDivision.apply(0), 0);
    }
}