package org.example.functions;

import operations.LeftSteppingDifferentialOperator;
import operations.MiddleSteppingDifferentialOperator;
import operations.RightSteppingDifferentialOperator;
import operations.SteppingDifferentialOperator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SteppingDifferentialOperatorTest {
    MathFunction cos = new CosMathFunction();
    MathFunction log = new LogMathFunction();

    @Test
    void LeftSteppingDifferentialOperatorTest() {
        SteppingDifferentialOperator leftOperation = new LeftSteppingDifferentialOperator(5);
        MathFunction differentialCos = leftOperation.derive(cos);
        MathFunction differentialLog = leftOperation.derive(log);

        assertEquals(0.238789, differentialCos.apply(1), 0.6);
        assertEquals(0.298627, differentialLog.apply(6), 0.6);
    }

    @Test
    void RightSteppingDifferentialOperatorTest() {
        SteppingDifferentialOperator rightOperation = new RightSteppingDifferentialOperator(5);
        MathFunction differentialCos = rightOperation.derive(cos);
        MathFunction differentialLog = rightOperation.derive(log);

        assertEquals(0.0839736, differentialCos.apply(1), 0.7);
        assertEquals(0.121227, differentialLog.apply(6), 0.6);
    }

    @Test
    void MiddleSteppingDifferentialOperatorTest() {
        SteppingDifferentialOperator middleOperation = new MiddleSteppingDifferentialOperator(5);
        MathFunction differentialCos = middleOperation.derive(cos);
        MathFunction differentialLog = middleOperation.derive(log);

        assertEquals(0.161381, differentialCos.apply(1), 0.6);
        assertEquals(0.23979, differentialLog.apply(6), 0.5);
    }


}
