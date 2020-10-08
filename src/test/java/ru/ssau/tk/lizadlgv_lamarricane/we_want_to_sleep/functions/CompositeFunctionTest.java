package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import org.testng.annotations.Test;

import org.testng.asserts.SoftAssert;

public class CompositeFunctionTest {
    private final static double DELTA = 0.0001;
    private final MathFunction identity = new IdentityFunction();
    private final MathFunction root = new FourthRootFunction();
    private final MathFunction constant = new ConstantFunction(5);
    private final MathFunction sqr = new SqrFunction();
    private final MathFunction division = new DivisionFunction();

    private final MathFunction identityRoot = new CompositeFunction(identity, root);
    private final MathFunction rootIdentity = new CompositeFunction(root, identity);
    private final MathFunction rootConstant = new CompositeFunction(root, constant);
    private final MathFunction sqrDivision = new CompositeFunction(sqr, division);
    private final MathFunction divisionSqr = new CompositeFunction(division, sqr);
    private final MathFunction sqrSqr = new CompositeFunction(sqr, sqr);
    private final MathFunction divisionDivision = new CompositeFunction(division, division);

    @Test
    public void testApply() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(identityRoot.apply(0.0), 0.0);
        softAssert.assertEquals(identityRoot.apply(507), 4.7451, DELTA);
        softAssert.assertEquals(rootIdentity.apply(16), 2.0);
        softAssert.assertEquals(rootIdentity.apply(0.2), 0.6687, DELTA);
        softAssert.assertEquals(rootConstant.apply(57), 5.0);
        softAssert.assertEquals(sqrDivision.apply(4), 8.0);
        softAssert.assertEquals(divisionSqr.apply(34), 289.0);
        softAssert.assertEquals(sqrDivision.apply(4), 8.0);
        softAssert.assertEquals(sqrSqr.apply(6), 1296.0);
        softAssert.assertEquals(divisionDivision.apply(100), 25.0);

        softAssert.assertAll();
    }
}