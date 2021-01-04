package interpolation_method;

import entity.UniVariableRealFunction;
import entity.UniVariableRealFunctionInterpolator;

public abstract class AbstractInterpolator
        implements UniVariableRealFunctionInterpolator {
    int amountOfNodes = 0;
    public UniVariableRealFunction resultFunction;
    double[] valuesOfInterpolatedFunction;
    double[] valuesOfArgument;
    double[] valuesOfFunctionInNodes;
    double[] nodes;
    double[] differenceBetweenFunctionAndInterpolation;

    public void setDefaultAmountsOfSegments(int defaultAmountsOfSegments) {
        DEFAULT_AMOUNTS_OF_SEGMENTS = defaultAmountsOfSegments;
    }

    public int DEFAULT_AMOUNTS_OF_SEGMENTS = 1000;

    public int getAmountOfNodes() {
        return amountOfNodes;
    }

    public double[] getValuesOfFunctionInNodes() {
        return valuesOfFunctionInNodes;
    }

    public double[] getNodes() {
        return nodes;
    }

    public double[] getValuesOfInterpolatedFunction() {
        return valuesOfInterpolatedFunction;
    }

    public double[] getValuesOfArgument() {
        return valuesOfArgument;
    }

    public double[] calculateDifferenceBetweenFunctionAndInterpolation() {

            double[] differenceBetweenFunctionAndInterpolation = new double[amountOfNodes];
            for (int i = 0; i < nodes.length; i++) {
                differenceBetweenFunctionAndInterpolation[i] = Math.abs(valuesOfFunctionInNodes[i]
                        - resultFunction.value(nodes[i]));
            }
            this.differenceBetweenFunctionAndInterpolation = differenceBetweenFunctionAndInterpolation;
            return differenceBetweenFunctionAndInterpolation;

    }

    public double[] getDifferenceBetweenFunctionAndInterpolation() {
        return this.differenceBetweenFunctionAndInterpolation;
    }
}
