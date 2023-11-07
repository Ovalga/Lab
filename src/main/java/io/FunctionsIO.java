package io;

import org.example.functions.Point;
import org.example.functions.TabulatedFunction;

import java.io.BufferedWriter;
import java.io.PrintWriter;

final class FunctionsIO {
    private FunctionsIO() {
        throw new UnsupportedOperationException();
    }

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) {
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.println(function.getCount());

        for (Point point : function) {
            printWriter.printf("%f %f\n", point.x, point.y);
        }
        printWriter.flush();
    }
}
