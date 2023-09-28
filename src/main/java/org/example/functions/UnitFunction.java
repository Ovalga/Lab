package org.example.functions;

public class UnitFunction extends ConstantFunction {
    public UnitFunction() {
        super(1);
    }

    public double apply(double x) {
        return 1;
    }
}
