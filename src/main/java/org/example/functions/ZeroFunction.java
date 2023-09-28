package org.example.functions;

public class ZeroFunction extends ConstantFunction {

    public ZeroFunction(double x) {
        super(x);
    }

    public double apply(double x) {
        return 0;
    }
}
