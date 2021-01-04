package interpolation_method;

import entity.UniVariableRealFunction;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import util.Utils;

public class SplineInterpolationMethod extends AbstractInterpolator {

    @Override
    public double[] interpolate(UniVariableRealFunction function,
                                double startOfSegment,
                                double endOfSegment,
                                int amountOfNodes) {
        this.amountOfNodes = amountOfNodes;

        double[] xOriginal = Utils.getArrayOfArguments(startOfSegment,
                endOfSegment,
                DEFAULT_AMOUNTS_OF_SEGMENTS);
        this.valuesOfArgument = xOriginal.clone();
        this.nodes = Utils.getArrayOfArguments(startOfSegment,
                endOfSegment,
                amountOfNodes);
        this.valuesOfFunctionInNodes =
                Utils.getArrayOfValuesOfUniVariableRealFunction(
                        function,
                this.nodes);

        return interpolate(this.valuesOfFunctionInNodes, this.nodes);
    }

    @Override
    public double[] interpolate(
            double[] valuesOfFunction,
            double[] valuesOfArgument) {
        SplineInterpolator interpolator = new SplineInterpolator();
        PolynomialSplineFunction splineFunction =
                interpolator.interpolate(
                        valuesOfArgument,
                valuesOfFunction);
        this.resultFunction = splineFunction::value;
        this.valuesOfInterpolatedFunction =
                Utils.getArrayOfValuesOfUniVariableRealFunction(resultFunction,
                        this.valuesOfArgument);
        return valuesOfInterpolatedFunction;
    }
}
