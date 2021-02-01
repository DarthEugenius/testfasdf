package boundary_value_problem_solvers;

import entity.BoundaryValueProblemSolver;
import entity.UniVariableRealFunction;
import org.apache.commons.math3.util.FastMath;
import util.Utils;

public class FiniteDifferenceMethod implements BoundaryValueProblemSolver {

    @Override
    public double[] solve(double epsilon,
                          UniVariableRealFunction p,
                          UniVariableRealFunction q,
                          UniVariableRealFunction f,
                          double startOfSegment,
                          double endOfSegment,
                          double alphaFirst,
                          double alphaSecond,
                          double betaFirst,
                          double betaSecond,
                          double gammaFirst,
                          double gammaSecond,
                          double step) {
        double[] arrayOfArguments = Utils.getArrayOfArguments(startOfSegment,
                endOfSegment,
                step);
        double[] aArray = new double[arrayOfArguments.length];
        double[] bArray = new double[arrayOfArguments.length];
        double[] cArray = new double[arrayOfArguments.length];
        double[] bVector = new double[arrayOfArguments.length];
        aArray[0] = step * alphaSecond + alphaFirst;
        bArray[0] = -alphaFirst;
        cArray[0] = 0;
        bVector[0] = step * gammaFirst;
        for (int i = 1; i < arrayOfArguments.length - 1; i++) {
            double xCurrent = arrayOfArguments[i];
            cArray[i] = 1 - step / 2 * p.value(xCurrent);
            aArray[i] = FastMath.pow(step, 2) * q.value(xCurrent) - 2;
            bArray[i] = 1 + step / 2 * p.value(xCurrent);
            bVector[i] = -FastMath.pow(step, 2) * f.value(xCurrent);
        }
        bArray[arrayOfArguments.length - 1] = 0;
        cArray[arrayOfArguments.length - 1] = -betaFirst;
        aArray[arrayOfArguments.length - 1] = step * betaSecond + betaFirst;
        bVector[arrayOfArguments.length - 1] = step * gammaSecond;
        return ThomasMethod.solve(
                aArray,
                bArray,
                cArray,
                bVector);
    }

    static class ThomasMethod {
        static double[] solve(double[] aArray,
                       double[] bArray,
                       double[] cArray,
                       double[] bVector) {
            double alpha;
            double[] xVector = new double[bVector.length];
            for (int i = 1; i < bVector.length; i++) {
                alpha = cArray[i - 1] / (-aArray[i - 1]);
                aArray[i] += alpha * bArray[i - 1]; // -a[i]-c[i]*P[i]
                bVector[i] += alpha * bVector[i - 1]; // c[i]*Q[i]-d[i]
            }
            xVector[bVector.length - 1] = bVector[bVector.length - 1] / aArray[bVector.length - 1];
            for (int i = bVector.length - 2; i >= 0; i--) {
                xVector[i] = (bVector[i] - bArray[i] * xVector[i + 1]) / aArray[i];
            }
            return xVector;
        }
    }
}
