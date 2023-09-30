package org.example.functions;

abstract class AbstractTabulatedFunction {
    protected abstract double floorIndexOfX(double x);
    protected abstract double extrapolateLeft(double x);
    protected abstract double extrapolateRight(double x);
    protected abstract double interpolate(double x, int floorIndex);
    protected abstract double interpolate(double x, double leftX, double rightX, double leftY, double rightY);
    protected abstract double apply(double x);

}
