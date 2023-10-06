package org.example.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompositeFunctionTest {
    CosMathFunction cosMathFunction = new CosMathFunction();
    LogMathFunction logMathFunction = new LogMathFunction();

    @Test
    void apply() {
        CompositeFunction compositeFunction = new CompositeFunction(cosMathFunction, logMathFunction);
        assertEquals(0, compositeFunction.apply(0));
        assertNotEquals(0, compositeFunction.apply(Math.PI / 3));

        CompositeFunction compositeFunction2 = new CompositeFunction(logMathFunction, cosMathFunction);
        assertEquals(1, compositeFunction2.apply(1));
        assertNotEquals(0, compositeFunction2.apply(Math.E));
    }

    @Test
    void andThen() {
        assertEquals(1.0, cosMathFunction.andThen(logMathFunction).apply(1.0));
        assertNotEquals(0.0, cosMathFunction.andThen(logMathFunction).apply(1.0));

        assertEquals(0.0, logMathFunction.andThen(cosMathFunction).apply(0.0));
        assertNotEquals(1, logMathFunction.andThen(cosMathFunction).apply(0));
    }

    @Test
    public void andThanList() {
        double[] xValue = {0, 1.5, 2, 2.5, 3};
        double[] yValue = {1, 0, 4, 5, 6};
        MathFunction myTestFunction = new LinkedListTabulatedFunction(xValue, yValue);
        MathFunction logTestFunction = new LogMathFunction();
        MathFunction cosTestFunction = new CosMathFunction();
        MathFunction logMy = logTestFunction.andThen(myTestFunction);
        MathFunction cosMy = cosTestFunction.andThen(myTestFunction);

        assertEquals(0, logMy.apply(0));
        assertNotEquals(1, logMy.apply(0));
        assertEquals(1, cosMy.apply(1.5));
        assertNotEquals(0, cosMy.apply(1.5));
    }

    @Test
    public void andThanArray() {
        double[] xValue = {0, 1.5, 2, 2.5, 3};
        double[] yValue = {1, 0, 4, 5, 6};
        MathFunction myTestFunction = new ArrayTabulatedFunction(xValue, yValue);
        MathFunction logTestFunction = new LogMathFunction();
        MathFunction cosTestFunction = new CosMathFunction();
        MathFunction logMy = logTestFunction.andThen(myTestFunction);
        MathFunction cosMy = cosTestFunction.andThen(myTestFunction);

        assertEquals(0, logMy.apply(0));
        assertNotEquals(1, logMy.apply(0));
        assertEquals(1, cosMy.apply(1.5));
        assertNotEquals(0, cosMy.apply(1.5));
    }
}