package org.example.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogMathFunctionTest {

    @Test
    void apply() {
        LogMathFunction logMathFunction = new LogMathFunction();
        assertEquals(0, logMathFunction.apply(1));
        assertNotEquals(2, logMathFunction.apply(Math.E));
    }
}