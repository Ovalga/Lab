package io;

import org.example.functions.Point;
import org.example.functions.TabulatedFunction;
 import org.example.functions.factory.TabulatedFunctionFactory;

import java.io.*;

final class FunctionsIO {
    private FunctionsIO() {
        throw new UnsupportedOperationException();
    }

    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        DataOutputStream out = new DataOutputStream(outputStream);
        out.writeInt(function.getCount());
        for (Point point : function) {
            out.writeDouble(point.x);
            out.writeDouble(point.y);
        }
        out.flush();
    }

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) {
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.println(function.getCount());
        for (Point point : function) {
            printWriter.printf("%f %f\n", point.x, point.y);
        }
        printWriter.flush();
    }
    public static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream,TabulatedFunctionFactory factory)throws IOException{

        DataInputStream in = new DataInputStream(inputStream);
        int length=in.readInt();
        double[] xValue=new double[length];
        double[] yValue=new double[length];
        for(int i=0;i<length;i++)
        {
            xValue[i]=in.readDouble();
            yValue[i]=in.readDouble();
        }
        return factory.create(xValue,yValue);
    }

}
