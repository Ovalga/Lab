package gui_webcalculation.controllers;

import org.example.functions.AbstractTabulatedFunction;
import org.example.functions.TabulatedFunction;

public interface FunctionService {

    String serializeMathFunction(String type, int count, double xFrom, double xTo);

    String serializeTabulatedFunction(double[] x, double[] y);

    TabulatedFunction differentiateMathFunction(String type, int count, double xFrom, double xTo);

    TabulatedFunction differentiateFunction(double[] x, double[] y);

    TabulatedFunction differentiateFunction(TabulatedFunction tabulatedFunction);

    TabulatedFunction createTabulatedFunction(double[] x, double[] y);

    TabulatedFunction deserializeFunction(String str);

    void setSetting(String type);

    TabulatedFunction createTabulatedFunction(String type, int count, double xFrom, double xTo);

    TabulatedFunction calculateOperation(String type, TabulatedFunction firstTF, TabulatedFunction secondTF);

}
