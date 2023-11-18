import concurrent.SynchronizedTabulatedFunction;
import org.example.functions.ArrayTabulatedFunction;
import org.example.functions.LinkedListTabulatedFunction;
import org.example.functions.Point;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SynchronizedTabulatedFunctionTest {
    double[] xValue = {1, 1.5, 2, 2.5, 3};
    double[] yValue = {2, 3, 4, 5, 6};
    ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValue, yValue);
SynchronizedTabulatedFunction synchronizedTabulatedFunction=new SynchronizedTabulatedFunction(function);

    @Test
    void getCount() {
        assertEquals(5,synchronizedTabulatedFunction.getCount());
        assertNotEquals(0, synchronizedTabulatedFunction.getCount());
    }

    @Test
    void getX() {
        assertEquals(1.5,synchronizedTabulatedFunction.getX(1));
        assertNotEquals(0, synchronizedTabulatedFunction.getX(3));
    }

    @Test
    void getY() {
        assertEquals(3, synchronizedTabulatedFunction.getY(1));
        assertNotEquals(0, synchronizedTabulatedFunction.getY(2));
    }

    @Test
    void setY() {
        synchronizedTabulatedFunction.setY(0, 7);
        assertEquals(7, synchronizedTabulatedFunction.getY(0));
        assertNotEquals(0, synchronizedTabulatedFunction.getY(0));
    }

    @Test
    void leftBound() {
        assertEquals(1, synchronizedTabulatedFunction.leftBound());
        assertNotEquals(0, synchronizedTabulatedFunction.leftBound());
    }

    @Test
    void rightBound() {
        assertEquals(3, synchronizedTabulatedFunction.rightBound());
        assertNotEquals(0, synchronizedTabulatedFunction.rightBound());
    }

    @Test
    void indexOfX() {
        assertEquals(1, synchronizedTabulatedFunction.indexOfX(1.5));
        assertNotEquals(0, synchronizedTabulatedFunction.indexOfX(1.5));


    }

    @Test
    void indexOfY() {
        assertEquals(1, synchronizedTabulatedFunction.indexOfY(3));
        assertNotEquals(0, synchronizedTabulatedFunction.indexOfY(3));


    }

    @Test
    void arrayTabulatedIteratorTestException() {
        Iterator<Point> iterator = synchronizedTabulatedFunction.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(xValue[i], point.x);
            assertEquals(yValue[i], point.y);
            ++i;
        }

    }

    @Test
    public void doSynchronously() {
        SynchronizedTabulatedFunction.Operation<Double> operation = func -> {
            double sum = 0;
            for (Point el : synchronizedTabulatedFunction)
                sum += el.y;
            return sum;
        };
        double sumOfY = synchronizedTabulatedFunction.doSynchronously(operation);
        assertEquals(20, sumOfY);
    }


    }
