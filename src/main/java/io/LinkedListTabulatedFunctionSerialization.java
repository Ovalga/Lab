package io;

import operations.TabulatedDifferentialOperator;
import org.example.functions.LinkedListTabulatedFunction;
import org.example.functions.TabulatedFunction;
import  org.example.functions.factory.TabulatedFunctionFactory;
import org.example.functions.factory.LinkedListTabulatedFunctionFactory;

import java.io.*;
import java.io.FileInputStream;

public class LinkedListTabulatedFunctionSerialization {
    public static void main(String[] args) {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("output/serialized linked list functions.bin"))) {
            double[] xValue = {1,3,5};
            double[] yValue = {2,4,9};

            TabulatedFunction function = new LinkedListTabulatedFunction(xValue, yValue);
            TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
            TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(factory);
            TabulatedFunction firstDerive = operator.derive(function);
            TabulatedFunction secondDerive = operator.derive(firstDerive);

            FunctionsIO.serialize(out, function);
            FunctionsIO.serialize(out, firstDerive);
            FunctionsIO.serialize(out, secondDerive);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("output/serialized linked list functions.bin"))) {
            System.out.println(FunctionsIO.deserialize(in));
            System.out.println(FunctionsIO.deserialize(in));
            System.out.println(FunctionsIO.deserialize(in));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
