package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IdentityFunctionTest {
    private final static double DELTA = 0.0001;
    private final IdentityFunction testIdentity = new IdentityFunction();

    @Test
    public void testApply() {
        assertEquals(testIdentity.apply(6), 6);
        assertEquals(testIdentity.apply(26.789), 26.789, DELTA);
        assertEquals(testIdentity.apply(507), 507);
        assertEquals(testIdentity.apply(-0.567), -0.567, DELTA);
    }
}