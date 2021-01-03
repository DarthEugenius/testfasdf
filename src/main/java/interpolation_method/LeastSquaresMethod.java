package interpolation_method;

import entity.UniVariableRealFunction;
import solve_system_of_linear_equations.GaussMethodDoubleImpl;
import util.Utils;


public class LeastSquaresMethod extends AbstractInterpolator {
    public static final int power = 4;


    public double scalarProduct(double[] vector1, double[] vector2) {
        if (vector1.length != vector2.length) {
            return 0;
        }
        double resultOfScalarProduct = 0.0;
        for (int i = 0; i < vector1.length; i++) {
            resultOfScalarProduct += vector1[i] * vector2[i];
        }
        return resultOfScalarProduct;
    }
    public double[] vectorPower(double[] vector, double power){
        double[] result = vector.clone();
        for (int i = 0; i < vector.length; i++) {
            result[i] = Math.pow(vector[i], power);
        }
        return result;
    }

    @Override
    public double[] interpolate(UniVariableRealFunction function, double startOfSegment, double endOfSegment, int amountOfNodes) {
        double[] x = Utils.getArrayOfArguments(startOfSegment, endOfSegment, amountOfNodes);
        double[] xOriginal = Utils.getArrayOfArguments(startOfSegment, endOfSegment, DEFAULT_AMOUNTS_OF_SEGMENTS);

        double[][] A = new double[power][power];
        double[] B = new double[power];
        double[] y = Utils.getArrayOfValuesOfUniVariableRealFunction(function, x);
        for (int i = 0; i < power; i++) {
            for (int j = 0; j < power; j++) {
                A[i][j] = 0;
                for (int k = 0; k < x.length; k++) {
                    A[i][j] += Math.pow(x[k], i + j);
                }
            }
            B[i] = 0;

            for (int j = 0; j < x.length; j++) {
                B[i] += scalarProduct(vectorPower(x, j), y);
            }
        }
        for (int i = 0; i < xOriginal.length; i++) {
            System.out.println(xOriginal[i]);
        }
        GaussMethodDoubleImpl gaussMethodDouble = new GaussMethodDoubleImpl(A, B, false);
        double[] solution = gaussMethodDouble.solve();
        this.valuesOfInterpolatedFunction = Utils.getArrayOfValuesOfUniVariableRealFunction(argument -> solution[0]
                + argument * solution[1]
                + Math.pow(argument, 2) * solution[2]
                + Math.pow(argument, 3) * solution[3], xOriginal);
        return this.valuesOfInterpolatedFunction;
    }

    @Override
    public double[] interpolate(double[] valuesOfFunction, double[] valuesOfArgument) {
        return new double[0];
    }
}
