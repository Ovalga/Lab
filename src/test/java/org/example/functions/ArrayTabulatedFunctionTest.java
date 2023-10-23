package org.example.functions;

import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;
import exceptions.InterpolationException;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTabulatedFunctionTest {
    double[] xValue = {1, 1.5, 2, 2.5, 3};
    double[] yValue = {2, 3, 4, 5, 6};
    ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xValue, yValue);


    @Test
    void getCount() {
        assertEquals(5, arrayTabulatedFunction.getCount());
        assertNotEquals(0, arrayTabulatedFunction.getCount());
    }

    @Test
    void getX() {
        assertEquals(1.5, arrayTabulatedFunction.getX(1));
        assertNotEquals(0, arrayTabulatedFunction.getX(3));
    }

    @Test
    void getY() {
        assertEquals(3, arrayTabulatedFunction.getY(1));
        assertNotEquals(0, arrayTabulatedFunction.getY(2));
    }

    @Test
    void setY() {
        arrayTabulatedFunction.setY(0, 7);
        assertEquals(7, arrayTabulatedFunction.getY(0));
        assertNotEquals(0, arrayTabulatedFunction.getY(0));
    }

    @Test
    void leftBound() {
        assertEquals(1, arrayTabulatedFunction.leftBound());
        assertNotEquals(0, arrayTabulatedFunction.leftBound());
    }

    @Test
    void rightBound() {
        assertEquals(3, arrayTabulatedFunction.rightBound());
        assertNotEquals(0, arrayTabulatedFunction.rightBound());
    }

    @Test
    void indexOfX() {
        assertEquals(1, arrayTabulatedFunction.indexOfX(1.5));
        assertNotEquals(0, arrayTabulatedFunction.indexOfX(1.5));


    }

    @Test
    void indexOfY() {
        assertEquals(1, arrayTabulatedFunction.indexOfY(3));
        assertNotEquals(0, arrayTabulatedFunction.indexOfY(3));


    }

    @Test
    void floorIndexOfX() {


        assertEquals(5, arrayTabulatedFunction.floorIndexOfX(10));
        assertNotEquals(0, arrayTabulatedFunction.floorIndexOfX(10));

        assertEquals(1, arrayTabulatedFunction.floorIndexOfX(1.5));
        assertNotEquals(0, arrayTabulatedFunction.floorIndexOfX(1.5));

        assertEquals(3, arrayTabulatedFunction.floorIndexOfX(2.7));
        assertNotEquals(0, arrayTabulatedFunction.floorIndexOfX(2.7));
    }

    @Test
    void interpolate() {
        assertEquals(3.4, arrayTabulatedFunction.interpolate(1.7, 2));
        assertNotEquals(0, arrayTabulatedFunction.interpolate(1.7, 2));


    }

    @Test
    void extrapolateLeft() {
        assertEquals(-10, arrayTabulatedFunction.extrapolateLeft(-5));
        assertNotEquals(0, arrayTabulatedFunction.extrapolateLeft(-5));


    }

    @Test
    void extrapolateRight() {
        assertEquals(20, arrayTabulatedFunction.extrapolateRight(10));
        assertNotEquals(0, arrayTabulatedFunction.extrapolateRight(10));
    }

    @Test
    void toStringTest() {
        assertEquals("(1.0;2.0) (1.5;3.0) (2.0;4.0) (2.5;5.0) (3.0;6.0) ", arrayTabulatedFunction.toString());
        assertNotEquals("(0;0)", arrayTabulatedFunction.toString());
    }

    @Test
    void equalsTest() {
        ArrayTabulatedFunction arrayTabulatedFunctionTest = new ArrayTabulatedFunction(xValue, yValue);
        assertTrue(arrayTabulatedFunction.equals(arrayTabulatedFunctionTest));
    }

    @Test
    void cloneTest() {
        Object arrayTabulatedFunctionTest = arrayTabulatedFunction.clone();
        assertTrue(arrayTabulatedFunction.equals(arrayTabulatedFunctionTest));
    }

    @Test
    void hashCodeTest() {
        ArrayTabulatedFunction arrayTabulatedFunction3 = new ArrayTabulatedFunction(xValue, yValue);
        assertEquals(arrayTabulatedFunction.hashCode(), arrayTabulatedFunction3.hashCode());
    }

    @Test
    void ArrayTwoTestException() {
        boolean exceptionThrown = false;
        double[] xValue2 = {5};
        double[] yValue2 = {2};
        try {
            ArrayTabulatedFunction arrTabulatedFunction2 = new ArrayTabulatedFunction(xValue2, yValue2);
        } catch (IllegalArgumentException exception) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    void ArrayFourTestException() {
        boolean exceptionThrown = false;
        LogMathFunction log = new LogMathFunction();
        try {
            ArrayTabulatedFunction arrkTabulatedFunction3 = new ArrayTabulatedFunction(log, 0.5, 1, 1);
        } catch (IllegalArgumentException exception) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }


    @Test
    void getXException() {
        boolean exceptionThrown = false;

        try {
            arrayTabulatedFunction.getX(10);
        } catch (IllegalArgumentException exception) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    void getYException() {
        boolean exceptionThrown = false;

        try {
            arrayTabulatedFunction.getY(-10);
        } catch (IllegalArgumentException exception) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    void setYException() {
        boolean exceptionThrown = false;

        try {
            arrayTabulatedFunction.setY(13, 10);
        } catch (IllegalArgumentException exception) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    void indexOfXException() {
        boolean exceptionThrown = false;

        try {
            arrayTabulatedFunction.indexOfX(2.21);
        } catch (NoSuchElementException exception) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    void floorIndexOfXException() {
        boolean exceptionThrown = false;

        try {
            arrayTabulatedFunction.floorIndexOfX(-2);
        } catch (IllegalArgumentException exception) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    void ArrayTabulatedFunctionLengthException() {
        double[] xValue2 = {5, 6, 5};
        double[] yValue2 = {2, 6, 7, 95};
        assertThrows(DifferentLengthOfArraysException.class, () -> {
            ArrayTabulatedFunction arrTabulatedFunction2 = new ArrayTabulatedFunction(xValue2, yValue2);
        });
    }

    @Test
    void ArrayTabulatedFunctionSortedException() {
        double[] xValue5 = {2, 3, 4, 17, 3, 45, 0};
        double[] yValue5 = {2, 34, 5, 56, 7, 6, 5};
        assertThrows(ArrayIsNotSortedException.class, () -> {
            ArrayTabulatedFunction arrTabulatedFunction25 = new ArrayTabulatedFunction(xValue5, yValue5);
        });
    }

    @Test
    void interpolateTestException() {
        assertThrows(InterpolationException.class, () -> {
            arrayTabulatedFunction.interpolate(2.5, 2);
        });
    }

    @Test
    void arrayTabulatedIteratorTestException() {
        Iterator<Point> iterator = arrayTabulatedFunction.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(xValue[i], point.x);
            assertEquals(yValue[i], point.y);
            ++i;
        }
        i = 0;
        for (Point point : arrayTabulatedFunction) {
            assertEquals(xValue[i], point.x);
            assertEquals(yValue[i], point.y);
            ++i;
        }

    }

}