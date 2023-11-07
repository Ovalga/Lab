package io;

import org.example.functions.TabulatedFunction;
import  org.example.functions.factory.TabulatedFunctionFactory;
import org.example.functions.factory.ArrayTabulatedFunctionFactory;
import org.example.functions.factory.LinkedListTabulatedFunctionFactory;
import operations.TabulatedDifferentialOperator;

import java.io.*;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) {
        try (BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream("input/binary function.bin")) ) {
            TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
            TabulatedFunction function = FunctionsIO.readTabulatedFunction(fileInput, factory);
            System.out.println(function);


        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
