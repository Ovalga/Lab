package org.example.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqrFunctionTest {

    @Test
    void apply() {
        SqrFunction sqrFunction = new SqrFunction();
        assertEquals(9,sqrFunction.apply(3));
        assertNotEquals(10,sqrFunction.apply(3));
    }
}