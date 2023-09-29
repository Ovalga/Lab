package org.example.functions;


public class ConstantFunction implements MathFunction {
    // double x;
 private final double x;
    public ConstantFunction(double argument) {
        this.x = argument;
    }

    public double getX() {
        return x;
    }

    public double apply(double value) {
        return getX();
    }
}
