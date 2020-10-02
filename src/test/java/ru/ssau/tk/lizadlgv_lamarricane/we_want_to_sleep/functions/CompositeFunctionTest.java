package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {
    private final static double DELTA = 0.0001;

    @Test
    public void testApply() {
        MathFunction identity = new IdentityFunction();
        MathFunction root = new FourthRootFunction();

        MathFunction identityRoot = new CompositeFunction(identity, root);
        MathFunction rootIdentity = new CompositeFunction(root, identity);

        assertEquals(identityRoot.apply(0), 0);
        assertEquals(identityRoot.apply(507), 4.7451, DELTA);
        assertEquals(rootIdentity.apply(16), 2);
        assertEquals(rootIdentity.apply(0.2), 0.6687, DELTA);
    }
}