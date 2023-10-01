package org.example.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantFunctionTest {

    @Test
    void apply() {
        UnitFunction unitFunction = new UnitFunction();
        assertEquals(1,unitFunction.apply(20));
        assertNotEquals(2,unitFunction.apply(20));
    }



    @Test
    void apply2() {
        ZeroFunction zeroFunction = new ZeroFunction();
        assertEquals(0,zeroFunction.apply(20));
        assertNotEquals(2,zeroFunction.apply(20));
    }
    @Test
    void apply3() {
        ConstantFunction constFunction = new ConstantFunction(5);
        assertEquals(5,constFunction.apply(20));
        assertNotEquals(2,constFunction.apply(20));
    }

}