package io;

import org.example.functions.ArrayTabulatedFunction;
import org.example.functions.LinkedListTabulatedFunction;
import org.example.functions.TabulatedFunction;
import org.example.functions.factory.ArrayTabulatedFunctionFactory;

import java.io.*;

public class TabulatedFunctionFileReader {
    public static void main(String[] args) {
        try {
            try (BufferedReader readerArray = new BufferedReader(new FileReader("input/function.txt"));
                 BufferedReader readerLinked = new BufferedReader(new FileReader("input/function.txt"))) {

                TabulatedFunction arrayTabulatedFunction = FunctionsIO.readTabulatedFunction(readerArray, new ArrayTabulatedFunctionFactory());
                TabulatedFunction linkedListTabulatedFunction = FunctionsIO.readTabulatedFunction(readerLinked, new org.example.functions.factory.LinkedListTabulatedFunctionFactory());

                System.out.println("Array:");
                System.out.println(arrayTabulatedFunction);
                System.out.println("Linked List:");
                System.out.println(linkedListTabulatedFunction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
