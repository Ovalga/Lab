package org.example.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CosMathFunctionTest {

    @Test
    void apply() {
        CosMathFunction cosMathFunction = new CosMathFunction();
        assertEquals(-1, cosMathFunction.apply(Math.PI));
        assertNotEquals(2, cosMathFunction.apply(Math.PI));

    }
}