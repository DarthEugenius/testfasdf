package interpolation_method;

import entity.UniVariableRealFunction;
import solve_system_of_linear_equations.GaussMethodDoubleImpl;
import util.Utils;


public class LeastSquaresMethod extends AbstractInterpolator {
    public static final int power = 4;


    @Override
    public double[] interpolate(UniVariableRealFunction function,
                                double startOfSegment,
                                double endOfSegment,
                                int amountOfNodes) {
        this.amountOfNodes = amountOfNodes;
        this.nodes = Utils.getArrayOfArguments(
                startOfSegment,
                endOfSegment,
                amountOfNodes);
        double[] xOriginal = Utils.getArrayOfArguments(
                startOfSegment,
                endOfSegment,
                DEFAULT_AMOUNTS_OF_SEGMENTS);
        this.valuesOfArgument = xOriginal;
        double[][] A = new double[power][power];
        double[] B = new double[power];
        this.valuesOfFunctionInNodes =
                Utils.getArrayOfValuesOfUniVariableRealFunction(
                        function,
                        nodes);
        for (int i = 0; i < power; i++) {
            for (int j = 0; j < power; j++) {
                for (double v : nodes) {
                    A[i][j] += Math.pow(v, i + j);
                }
            }
            for (int j = 0; j < nodes.length; j++) {
                B[i] += valuesOfFunctionInNodes[j] * Math.pow(nodes[j], i);
            }
        }
        GaussMethodDoubleImpl gaussMethodDouble =
                new GaussMethodDoubleImpl(
                        A,
                        B,
                        true);
        double[] solution = gaussMethodDouble.solve();
        this.resultFunction =
                argument -> solution[0]
                        + argument * solution[1]
                        + Math.pow(argument, 2) * solution[2]
                        + Math.pow(argument, 3) * solution[3];
        this.valuesOfInterpolatedFunction =
                Utils.getArrayOfValuesOfUniVariableRealFunction(
                        resultFunction,
                        xOriginal);
        return this.valuesOfInterpolatedFunction;
    }

    @Override
    public double[] interpolate(
            double[] valuesOfFunction,
            double[] valuesOfArgument) {
        return new double[0];
    }
}
