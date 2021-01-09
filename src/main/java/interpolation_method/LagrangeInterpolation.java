package interpolation_method;

import entity.UniVariableRealFunction;
import util.Utils;

public class LagrangeInterpolation extends AbstractApproximator {
    @Override
    public double[] approximate(UniVariableRealFunction function,
                                double startOfSegment,
                                double endOfSegment,
                                int amountOfNodes) {
        this.amountOfNodes = amountOfNodes;
        double[] xOriginal =
                Utils.getArrayOfArguments(
                        startOfSegment,
                        endOfSegment,
                        DEFAULT_AMOUNTS_OF_SEGMENTS);
        this.valuesOfArgument = xOriginal.clone();
        double[] x = Utils.getArrayOfArguments(startOfSegment,
                endOfSegment,
                amountOfNodes);
        this.nodes = x;
        double[] y =
                Utils.getArrayOfValuesOfUniVariableRealFunction(
                        function,
                        x);
        this.valuesOfFunctionInNodes = y;
        return approximate(y, x);
    }

    @Override
    public double[] approximate(double[] valuesOfFunction,
                                double[] valuesOfArgument) {
        this.resultFunction = x -> {
            double result = 0.0;
            int size = valuesOfArgument.length;
            for (int i = 0; i < size; i++) {
                double basicPolynomial = 1;
                for (int j = 0; j < size; j++) {
                    if (j != i) {
                        basicPolynomial *= (x - valuesOfArgument[j])
                                / (valuesOfArgument[i] - valuesOfArgument[j]);
                    }
                }
                result += basicPolynomial * valuesOfFunction[i];
            }
            return result;
        };

        this.valuesOfInterpolatedFunction =
                Utils.getArrayOfValuesOfUniVariableRealFunction(
                        resultFunction,
                        this.valuesOfArgument);
        return this.valuesOfInterpolatedFunction;
    }
}