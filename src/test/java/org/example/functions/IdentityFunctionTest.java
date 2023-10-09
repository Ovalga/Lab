package org.example.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IdentityFunctionTest {
    @Test
    void apply() {
        MathFunction identityFunction = new IdentityFunction();
        assertEquals(20, identityFunction.apply(20));
        assertNotEquals(1, identityFunction.apply(20));
    }

    @Test
    public void toStringTest() {
        MathFunction identityFunction = new IdentityFunction();
        String str = identityFunction.toString();
        boolean bool = str.equals("Класс, реализующий интерфейс MathFunction, объекты которого должны выполнять тождественное преобразование");
        assertTrue(bool);
    }

    @Test
    public void equalsTest() {
        MathFunction identityFunction = new IdentityFunction();
        MathFunction identityFunction2 = new IdentityFunction();
        boolean bool = identityFunction.equals(identityFunction2);
        assertTrue(bool);
    }

    @Test
    public void cloneTest() {
        IdentityFunction identityFunction = new IdentityFunction();
        boolean bool = identityFunction.equals(identityFunction.clone());
        assertTrue(bool);
    }

    @Test
    public void hashCodeTest() {
        IdentityFunction identityFunction = new IdentityFunction();
        IdentityFunction identityFunction2 = new IdentityFunction();
        assertEquals(1912, identityFunction.hashCode());
        assertEquals(1912, identityFunction2.hashCode());
    }

}