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

            double step = (xFrom - xTo) / (count - 1);
            for (int i = 0; i < count; ++i) {
                myArrayX[i] = xTo + (i * step);
                myArrayY[i] = source.apply(xTo + (i * step));
            }
            this.xValues = myArrayX;
            this.yValues = myArrayY;
        } else if (xTo > xFrom) {
            double step = (xTo - xFrom) / (count - 1);
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

    int getCount() {
        return count;
    }

    double getX(int index) {
        return xValues[index];
    }

    double getY(int index) {
        return yValues[index];
    }

    void setY(int index, double value) {
        yValues[index] = value;
    }

    double leftBound() {
        return xValues[0];
    }

    double rightBound() {
        return xValues[count - 1];
    }
    int indexOfX(double x){
       // int sizeArrayInIndex = count - 1;
        int index = 0;
        while(index<= count - 1){
            if(xValues[index] == x)
                return index;
            else index++;
        }
        return -1;
    }
    int indexOfY(double x){
        // int sizeArrayInIndex = count - 1;
        int index = 0;
        while(index<= count - 1){
            if(yValues[index] == x)
                return index;
            else index++;
        }
        return -1;
    }


}