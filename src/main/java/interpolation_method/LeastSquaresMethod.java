package interpolation_method;

import entity.UniVariableRealFunction;
import org.apache.commons.math3.util.FastMath;
import solve_system_of_linear_equations.GaussMethodDoubleImpl;
import util.Utils;


public class LeastSquaresMethod extends AbstractApproximator {
    // TODO
    public static final int POWER = 4;


    @Override
    public double[] approximate(UniVariableRealFunction function,
                                double startOfSegment,
                                double endOfSegment,
                                int amountOfNodes) {
        this.nodes = Utils.getArrayOfArguments(
                startOfSegment,
                endOfSegment,
                amountOfNodes
        );
        return approximate(
                Utils.getArrayOfValuesOfUniVariableRealFunction(
                        function,
                        this.nodes
                ),
                this.nodes
        );
    }

    @Override
    public double[] approximate(
            double[] valuesOfFunction,
            double[] valuesOfArgument) {
        double[] xOriginal = Utils.getArrayOfArguments(
                valuesOfArgument[0],
                valuesOfArgument[valuesOfArgument.length - 1],
                DEFAULT_AMOUNTS_OF_SEGMENTS);
        this.valuesOfFunctionInNodes = valuesOfFunction;
        this.valuesOfArgument = xOriginal;
        double[][] A = new double[POWER][POWER];
        double[] B = new double[POWER];
        for (int i = 0; i < POWER; i++) {
            for (int j = 0; j < POWER; j++) {
                for (double v : nodes) {
                    A[i][j] += FastMath.pow(v, i + j);
                }
            }
            for (int j = 0; j < nodes.length; j++) {
                B[i] += valuesOfFunction[j] * FastMath.pow(nodes[j], i);
            }
        }
        GaussMethodDoubleImpl gaussMethodDouble =
                new GaussMethodDoubleImpl(
                        A,
                        B,
                        true);
        double[] solutionCoefficients = gaussMethodDouble.solve();
        this.resultFunction = x -> {
            double result = 0.0;
            for (int i = 0; i < solutionCoefficients.length; i++) {
                result += solutionCoefficients[i] * FastMath.pow(x, i);
            }
            return result;
        };
        this.valuesOfInterpolatedFunction =
                Utils.getArrayOfValuesOfUniVariableRealFunction(
                        resultFunction,
                        xOriginal);
        return this.valuesOfInterpolatedFunction;
    }
}