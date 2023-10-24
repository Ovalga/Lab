package operations;

import org.example.functions.MathFunction;

public interface DifferentialOperator<T extends MathFunction>{
    T derive(T function);
}
