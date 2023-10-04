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

}