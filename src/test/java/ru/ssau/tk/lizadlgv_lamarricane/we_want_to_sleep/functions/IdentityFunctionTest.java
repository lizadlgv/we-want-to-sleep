package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IdentityFunctionTest {
    private final IdentityFunction testIdentity = new IdentityFunction();

    @Test
    public void testApply() {
        assertEquals(testIdentity.apply(6), 6);
        assertEquals(testIdentity.apply(26.789), 26.789);
        assertEquals(testIdentity.apply(507.0), 507.0);
        assertEquals(testIdentity.apply(-0.567), -0.567);
    }
}