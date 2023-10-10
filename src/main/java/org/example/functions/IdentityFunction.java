package org.example.functions;

public class IdentityFunction implements MathFunction, Cloneable {

    public double apply(double x) {
        return x;
    }

    @Override
    public boolean equals(Object o) {
        return this.getClass() == o.getClass();
    }

    @Override
    public Object clone() {
        return new IdentityFunction();
    }

    @Override
    public String toString() {
        return "IdentityFunction{}";
    }

    @Override
    public int hashCode() {
        return 1912;
    }
}



