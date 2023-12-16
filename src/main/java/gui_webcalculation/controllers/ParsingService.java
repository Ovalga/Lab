package gui_webcalculation.controllers;

import org.example.functions.TabulatedFunction;

public interface ParsingService {

    String getFirstPartOfCombinedString(String str);

    String getSecondPartOfCombinedString(String str);

    double[] parseStringToDoubleArray(String str);

    String parseTabulatedFunctionToString(TabulatedFunction function);

    String parseTwoTabulatedFunctionToString(TabulatedFunction function1,TabulatedFunction function2);


}