package gui_webcalculation.controllers;

import org.example.functions.TabulatedFunction;
import org.springframework.stereotype.Service;

@Service
public class ParsingServiceImplements implements ParsingService{

    @Override
    public String getFirstPartOfCombinedString(String str) {
        return str.split("\\|")[0];
    }

    @Override
    public String getSecondPartOfCombinedString(String str) {
        return str.split("\\|")[1];
    }

    @Override
    public double[] parseStringToDoubleArray(String str) {
        String[] numberStrings = str.split("_");
        double[] numbers = new double[numberStrings.length];
        for (int i = 0; i < numberStrings.length; i++) {
            numbers[i] = Double.parseDouble(numberStrings[i]);
        }
        return numbers;
    }

    @Override
    public String parseTabulatedFunctionToString(TabulatedFunction function) {
        String X = "";
        String Y = "";
        for (int i = 0; i<function.getCount(); ++i){
            X = X+function.getX(i)+"_";
            Y = Y+function.getY(i)+"_";
        }
        X = X.substring(0, X.length() - 1);
        Y = Y.substring(0, Y.length() - 1);
        return X+'|'+Y;
    }

    @Override
    public String parseTwoTabulatedFunctionToString(TabulatedFunction function1, TabulatedFunction function2) {
        String str = parseTabulatedFunctionToString(function1);
        str = str + ":" + parseTabulatedFunctionToString(function2);
        return str;
    }
}
