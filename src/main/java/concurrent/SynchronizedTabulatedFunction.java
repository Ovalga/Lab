package concurrent;

import org.example.functions.Point;
import org.example.functions.TabulatedFunction;

import java.util.Iterator;

public class SynchronizedTabulatedFunction implements TabulatedFunction {
    private final TabulatedFunction delegation;
    public SynchronizedTabulatedFunction(TabulatedFunction delegation
    ) {
        this.delegation=delegation;

    }
    @Override
    public int getCount()
    {
        synchronized (delegation){
            return delegation.getCount();
        }
    }
    @Override
    public double getX(int index)
    {
        synchronized (delegation){
            return delegation.getX(index);
        }
    }
    @Override
    public double getY(int index)
    {
        synchronized (delegation){
            return delegation.getY(index);
        }
    }
    @Override
    public void setY(int index,double value)
    {
        synchronized (delegation){
            delegation.setY(index,value);
        }
    }
    @Override
    public int indexOfX(double x)
    {
        synchronized (delegation){
            return delegation.indexOfX(x);
        }
    }
    @Override
    public int indexOfY(double y)
    {
        synchronized (delegation){
            return delegation.indexOfY(y);
        }
    }
    @Override
    public double rightBound()
    {
        synchronized (delegation){
            return delegation.rightBound();
        }
    }
    @Override
    public double leftBound()
    {
        synchronized (delegation){
            return delegation.leftBound();
        }
    }


    @Override
    public Iterator<Point> iterator() {
        return null;
    }

    @Override
    public double apply(double x) {
        return 0;
    }
}
