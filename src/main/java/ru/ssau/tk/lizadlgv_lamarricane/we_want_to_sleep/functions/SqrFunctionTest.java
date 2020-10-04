package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import static org.testng.Assert.*;

import org.junit.Test;

public class SqrFunctionTest {

    @Test
    public void testApply() {
        SqrFunction testFunction = new SqrFunction();
        assertEquals (testFunction.apply(5), 25);
        assertEquals (testFunction.apply(-11), 121);
        assertEquals (testFunction.apply(0.1), 0.01);
        assertEquals (testFunction.apply(-1), 1);
    }
}