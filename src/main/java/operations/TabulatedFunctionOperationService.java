package operations;

import org.example.functions.TabulatedFunction;
import org.example.functions.Point;

public class TabulatedFunctionOperationService {
    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int i = 0;
        Point[] asPointsArray = new Point[]{};
        for (Point num : tabulatedFunction) {
            asPointsArray[i] = num;
            ++i;
        }
        return asPointsArray;
    }
}
