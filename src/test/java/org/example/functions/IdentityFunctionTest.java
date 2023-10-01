package org.example.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentityFunctionTest {
    @Test
    void apply() {
        MathFunction  identityFunction = new IdentityFunction();
        assertEquals(20,identityFunction.apply(20));
        assertNotEquals(1,identityFunction.apply(20));
    }

}