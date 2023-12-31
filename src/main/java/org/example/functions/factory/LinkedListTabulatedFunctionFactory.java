package org.example.functions.factory;

import org.example.functions.LinkedListTabulatedFunction;
import org.example.functions.TabulatedFunction;

public class LinkedListTabulatedFunctionFactory implements org.example.functions.factory.TabulatedFunctionFactory {
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }
}
