package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.*;

public class MathFunctionTest {

    private final MathFunction identity = new IdentityFunction();
    private final MathFunction root = new FourthRootFunction();
    private final MathFunction constant = new ConstantFunction(3);
    private final MathFunction division = new DivisionFunction();
    private final MathFunction sqr = new SqrFunction();
    private final MathFunction unit = new UnitFunction();
    private final MathFunction composite = identity.andThen(sqr).andThen(root);

    @Test
    public void testAndThen() {

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(composite.apply(4), 2.0);
        softAssert.assertEquals(division.andThen(sqr).apply(16), 64.0);
        softAssert.assertEquals(root.andThen(constant).apply(7), 3.0);
        softAssert.assertEquals(unit.andThen(division).andThen(identity).apply(67), 0.5);

        softAssert.assertAll();
    }
}