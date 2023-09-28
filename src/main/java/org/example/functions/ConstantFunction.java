package org.example.functions;


public class ConstantFunction implements MathFunction {
    private final double x;

    public ConstantFunction(double x) {
        this.x = 5;
    }

    public double getX() {
        return x;
    }

    public double apply(double x) {
        return this.x;
    }
}
