package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FourthRootFunctionTest {
    private final static double DELTA = 0.0001;
    private final FourthRootFunction testFourthRoot = new FourthRootFunction();

    @Test
    public void testApply() {
        assertEquals(testFourthRoot.apply(16), 2);
        assertEquals(testFourthRoot.apply(0.2), 0.6687, DELTA);
        assertEquals(testFourthRoot.apply(507), 4.7451, DELTA);

    }
}