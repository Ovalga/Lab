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

    int indexOfX(double x) {
        int index = 0;
        while (index <= count - 1) {
            if (xValues[index] == x) return index;
            else index++;
        }
        return -1;
    }

    int indexOfY(double y) {
        int index = 0;
        while (index <= count - 1) {
            if (yValues[index] == y) return index;
            else index++;
        }
        return -1;
    }

    int floorIndexOfX(double x) {
        if (xValues[0] > x) return 0;
        else if (xValues[count - 1] < x) return count;
        else {
            for (int index = 0; ; index++) {
                if (xValues[index] == x) return index;
                else if (xValues[index] > x) return index - 1;
            }
        }
    }

    double interpolate(double x, int floorIndex) {
        if (count == 1) return yValues[0];
        else
            return (yValues[floorIndex - 1] + (((yValues[floorIndex] - yValues[floorIndex - 1]) / (xValues[floorIndex] - xValues[floorIndex - 1])) * (x - xValues[floorIndex - 1])));
    }

    double extrapolateLeft(double x) {
        if (count == 1) return yValues[0];
        else
            return (yValues[0] + (((yValues[1] - yValues[0]) / (xValues[1] - xValues[0])) * (x - xValues[0])));
    }

    double extrapolateRight(double x) {
        if (count == 1) return yValues[0];
        else
            return (yValues[count - 2] + (((yValues[count - 1] - yValues[count - 2]) / (xValues[count - 1] - xValues[count - 2])) * (x - xValues[count - 2])));
    }

    private double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        if (count == 1) return yValues[0];
        else
            return (leftY + (((rightY - leftY) / (rightX - leftX)) * (x - leftX)));
    }
}