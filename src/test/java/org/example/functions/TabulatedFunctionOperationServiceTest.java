package org.example.functions;

import operations.TabulatedFunctionOperationService;
import org.junit.jupiter.api.Test;

public class TabulatedFunctionOperationServiceTest {
    double[] xValue = {1, 1.5, 2, 2.5, 3};
    double[] yValue = {2, 3, 4, 5, 6};
    ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xValue, yValue);
    TabulatedFunctionOperationService tabulatedFunctionOperationService = new TabulatedFunctionOperationService();
    @Test
    void asPointsTest(){
        tabulatedFunctionOperationService.asPoints(arrayTabulatedFunction);
    };

}
