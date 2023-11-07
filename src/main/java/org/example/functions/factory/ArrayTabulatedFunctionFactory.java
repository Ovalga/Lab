package org.example.functions.factory;

import org.example.functions.ArrayTabulatedFunction;
import org.example.functions.TabulatedFunction;

public class ArrayTabulatedFunctionFactory implements org.example.functions.factory.TabulatedFunctionFactory {
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new ArrayTabulatedFunction(xValues, yValues);
    }
}
