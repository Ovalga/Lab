package org.example.functions;

import java.util.Arrays;

public class ArrayTabulatedFunction {
    private double[] xValues;
    private double[] yValues;
    private int count;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);
        this.count = xValues.length;

    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        this.count = count;
        double[] myArrayX = new double[count];
        double[] myArrayY = new double[count];
        if (xFrom > xTo) {

            double step = (xFrom - xTo) / count;
            for (int i = 0; i < count; ++i) {
                myArrayX[i] = xTo + (i * step);
                myArrayY[i] = source.apply(xTo + (i * step));
            }
            this.xValues = myArrayX;
            this.yValues = myArrayY;
        } else if (xTo > xFrom) {
            double step = (xTo - xFrom) / count;
            for (int i = 0; i < count; ++i) {
                myArrayX[i] = xFrom + (i * step);
                myArrayY[i] = source.apply(xFrom + (i * step));
            }
            this.xValues = myArrayX;
            this.yValues = myArrayY;
        } else {
            for (int i = 0; i < count; ++i) {
                myArrayX[i] = xFrom;
                myArrayY[i] = source.apply(xFrom);
            }
            this.xValues = myArrayX;
            this.yValues = myArrayY;
        }

    }
}