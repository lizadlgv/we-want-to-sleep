package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AbstractTabulatedFunctionTest {
    private final static double DELTA = 0.0001;

    public MockTabulatedFunction mockFunction = new MockTabulatedFunction();

    @Test
    public void testInterpolate() {
        assertEquals(mockFunction.interpolate(3, 6, 9, 7, 3), 11, DELTA);
    }

    @Test
    public void testApply() {
        assertEquals(mockFunction.apply(7), 9, DELTA);
        assertEquals(mockFunction.apply(-7), -19, DELTA);
        assertNotEquals(mockFunction.apply(2), 1, DELTA);
        assertNotEquals(mockFunction.apply(1), 5, DELTA);
    }
}
