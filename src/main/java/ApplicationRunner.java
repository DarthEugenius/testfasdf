import entity.UniVariableRealFunction;
import interpolation_method.AbstractInterpolator;
import interpolation_method.LeastSquaresMethod;
import interpolation_method.SplineInterpolationMethod;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import org.knowm.xchart.XYSeries;
import util.Utils;


public class ApplicationRunner {

    public static void main(String[] args) {

       /* FindZerosOfUniVariableRealFunctionApplication.run(
                x -> x * (x - 4) * (x - 3),
                -2,
                1,
                1e-10
        );*/
        UniVariableRealFunction function = x -> Math.sqrt(2 * x) - Math.sin(5 * x);

        AbstractInterpolator interpolationMethod = new LeastSquaresMethod();
        double[] interpolated = interpolationMethod.interpolate(
                function,
                0.0,
                5.0,
                3
        );
        double[] arrayOfArguments = Utils.getArrayOfArguments(0, 5, 1000);
        double[] arrayOfValuesOfUniVariableRealFunction = Utils.getArrayOfValuesOfUniVariableRealFunction(
                function,
                arrayOfArguments);
        final XYChart chart = QuickChart.getChart(
                "Sample Chart",
                "X",
                "Y",
                "y(x)", arrayOfArguments, arrayOfValuesOfUniVariableRealFunction);

        XYSeries series = chart.addSeries("interpolated",
                interpolationMethod.getValuesOfArgument(),
                interpolationMethod.getValuesOfInterpolatedFunction());
        chart.getStyler().setZoomEnabled(true);
        chart.getStyler().setMarkerSize(0);
        new SwingWrapper(chart).displayChart();
    }
}




