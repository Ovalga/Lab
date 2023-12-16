package gui_webcalculation.controllers;

import org.example.functions.*;
import org.example.functions.factory.ArrayTabulatedFunctionFactory;
import org.example.functions.factory.LinkedListTabulatedFunctionFactory;
import operations.TabulatedDifferentialOperator;
import operations.TabulatedFunctionOperationService;
import org.springframework.stereotype.Service;


import java.io.*;
import java.util.Base64;
import java.util.HashMap;


@Service
public class FunctionServiceImplements implements FunctionService{

    private static String type = "ArrayTabulatedFunction";

    private final HashMap<String, MathFunction> typeMap = new HashMap<String, MathFunction>(){{
        put("UnitFunction", new UnitFunction());
        put("SqrFunction", new SqrFunction());
        put("ZeroFunction", new ZeroFunction());
        put("IdentityFunction", new IdentityFunction());
    }};

    private ArrayTabulatedFunctionFactory arrayTabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();

    private LinkedListTabulatedFunctionFactory linkedListTabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory();

    private TabulatedFunctionOperationService tabulatedFunctionOperationService = new TabulatedFunctionOperationService();

    @Override
    public String serializeMathFunction(String type, int count, double xFrom, double xTo) {
        AbstractTabulatedFunction ATF = null;
        if (this.type == "ArrayTabulatedFunction"){
            ATF = new ArrayTabulatedFunction(typeMap.get(type),xFrom,xTo,count);
        } else {
            ATF = new LinkedListTabulatedFunction(typeMap.get(type),xFrom,xTo,count);
        }
        String serializedObject = "Ошибка сериализации";
        ATF.hashCode();
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(ATF);
            oos.flush();
            byte[] bytes = bos.toByteArray();
            serializedObject = Base64.getEncoder().encodeToString(bytes);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return serializedObject;
    }

    @Override
    public String serializeTabulatedFunction(double[] x, double[] y) {
        TabulatedFunction ATF = null;
        if (this.type == "ArrayTabulatedFunction"){
            ATF = new ArrayTabulatedFunction(x,y);
        } else {
            ATF = new LinkedListTabulatedFunction(x,y);
        }
        String serializedObject = "Ошибка сериализации";
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(ATF);
            oos.flush();
            byte[] bytes = bos.toByteArray();
            serializedObject = Base64.getEncoder().encodeToString(bytes);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return serializedObject;
    }

    @Override
    public TabulatedFunction differentiateMathFunction(String type, int count, double xFrom, double xTo) {
        TabulatedFunction TF = null;
        if (this.type == "ArrayTabulatedFunction"){
            TabulatedDifferentialOperator tabulatedDifferentialOperator = new TabulatedDifferentialOperator(arrayTabulatedFunctionFactory);
            TF = new ArrayTabulatedFunction(typeMap.get(type),xFrom,xTo,count);
            TabulatedFunction derive = tabulatedDifferentialOperator.derive(TF);
            return derive;
        } else {
            TabulatedDifferentialOperator tabulatedDifferentialOperator = new TabulatedDifferentialOperator(linkedListTabulatedFunctionFactory);
            TF = new LinkedListTabulatedFunction(typeMap.get(type),xFrom,xTo,count);
            TabulatedFunction derive = tabulatedDifferentialOperator.derive(TF);
            return derive;
        }
    }

    @Override
    public TabulatedFunction differentiateFunction(double[] x, double[] y) {
        TabulatedFunction derive = null;
        TabulatedDifferentialOperator tabulatedDifferentialOperator;
        TabulatedFunction TF;
        if (this.type == "ArrayTabulatedFunction"){
            tabulatedDifferentialOperator = new TabulatedDifferentialOperator(arrayTabulatedFunctionFactory);
            TF = new ArrayTabulatedFunction(x, y);
        } else {
            tabulatedDifferentialOperator = new TabulatedDifferentialOperator(linkedListTabulatedFunctionFactory);
            TF = new LinkedListTabulatedFunction(x, y);
        }
        derive = tabulatedDifferentialOperator.derive(TF);
        return derive;
    }

    @Override
    public TabulatedFunction differentiateFunction(TabulatedFunction tabulatedFunction) {
        TabulatedFunction derive = null;
        TabulatedDifferentialOperator tabulatedDifferentialOperator;
        if (this.type == "ArrayTabulatedFunction"){
            tabulatedDifferentialOperator = new TabulatedDifferentialOperator(arrayTabulatedFunctionFactory);
        } else {
            tabulatedDifferentialOperator = new TabulatedDifferentialOperator(linkedListTabulatedFunctionFactory);
        }
        derive = tabulatedDifferentialOperator.derive(tabulatedFunction);
        return derive;
    }

    @Override
    public TabulatedFunction createTabulatedFunction(double[] x, double[] y) {
        TabulatedFunction TF = null;
        if (this.type == "ArrayTabulatedFunction"){
            TF = new ArrayTabulatedFunction(x,y);
        } else {
            TF = new LinkedListTabulatedFunction(x,y);
        }
        return TF;
    }

    @Override
    public TabulatedFunction createTabulatedFunction(String type, int count, double xFrom, double xTo) {
        AbstractTabulatedFunction ATF = null;
        if (this.type == "ArrayTabulatedFunction"){
            ATF = new ArrayTabulatedFunction(typeMap.get(type),xFrom,xTo,count);
        } else {
            ATF = new LinkedListTabulatedFunction(typeMap.get(type),xFrom,xTo,count);
        }
        return ATF;
    }

    @Override
    public TabulatedFunction calculateOperation(String type, TabulatedFunction firstTF, TabulatedFunction secondTF) {
        TabulatedFunction result = null;
        if (this.type == "ArrayTabulatedFunction"){
            tabulatedFunctionOperationService.setFactory(arrayTabulatedFunctionFactory);
        } else {
            tabulatedFunctionOperationService.setFactory(linkedListTabulatedFunctionFactory);
        }
        switch (type) {
            case "addition":
                result = tabulatedFunctionOperationService.add(firstTF,secondTF);
                break;
            case "subtraction":
                result = tabulatedFunctionOperationService.subtraction(firstTF,secondTF);
                break;
            case "multiplication":
                result = tabulatedFunctionOperationService.multiplication(firstTF,secondTF);
                break;
            case "division":
                result = tabulatedFunctionOperationService.division(firstTF,secondTF);
                break;
        }
        return result;
    }

    @Override
    public TabulatedFunction deserializeFunction(String str) {
        TabulatedFunction ATF = null;
        try {
            byte[] bytes = Base64.getDecoder().decode(str);
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            if (this.type == "ArrayTabulatedFunction"){
                ATF = (ArrayTabulatedFunction) ois.readObject();
            } else {
                ATF = (LinkedListTabulatedFunction) ois.readObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ATF;
    }

    @Override
    public void setSetting(String type) {
        this.type = type;
    }


}
