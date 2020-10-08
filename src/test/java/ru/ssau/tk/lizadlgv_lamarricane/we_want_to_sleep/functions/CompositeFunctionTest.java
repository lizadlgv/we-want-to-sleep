package ru.ssau.tk.lizadlgv_lamarricane.we_want_to_sleep.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import org.testng.asserts.SoftAssert;

public class CompositeFunctionTest {
    private final static double DELTA = 0.0001;

    @Test
    public void testApply() {
        MathFunction identity = new IdentityFunction();
        MathFunction root = new FourthRootFunction();
        MathFunction constant = new ConstantFunction(5);
        MathFunction sqr = new SqrFunction();
        MathFunction division = new DivisionFunction();

        MathFunction identityRoot = new CompositeFunction(identity, root);
        MathFunction rootIdentity = new CompositeFunction(root, identity);
        MathFunction rootConstant = new CompositeFunction(root, constant);
        MathFunction sqrDivision = new CompositeFunction(sqr, division);
        MathFunction divisionSqr = new CompositeFunction(division, sqr);
        MathFunction sqrSqr = new CompositeFunction(sqr, sqr);
        MathFunction divisionDivision = new CompositeFunction(division, division);

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