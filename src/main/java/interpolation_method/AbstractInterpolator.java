package interpolation_method;

import entity.UniVariableRealFunctionInterpolator;

public abstract class AbstractInterpolator
        implements UniVariableRealFunctionInterpolator {
    int amountOfNodes = 0;
    double[] valuesOfInterpolatedFunction;
    double[] valuesOfArgument;

    public static void setDefaultAmountsOfSegments(int defaultAmountsOfSegments) {
        DEFAULT_AMOUNTS_OF_SEGMENTS = defaultAmountsOfSegments;
    }

    public static int DEFAULT_AMOUNTS_OF_SEGMENTS = 1000;

    public int getAmountOfNodes() {
        return amountOfNodes;
    }

    public double[] getValuesOfInterpolatedFunction() {
        return valuesOfInterpolatedFunction;
    }

    public double[] getValuesOfArgument() {
        return valuesOfArgument;
    }

    public double[] getDifferenceBetweenFunctionAndInterpolation(){
        if (amountOfNodes > 0){
            double[] DifferenceBetweenFunctionAndInterpolation = new double[amountOfNodes];
            return DifferenceBetweenFunctionAndInterpolation;
        } else {
            return null;
        }
    }
}
