package running;

import entity.UniVariableRealFunction;
import entity.UniVariableRealFunctionExtremaFinder;
import entity.UniVariableRealFunctionZeroFinder;
import infrastructure.factories.AllInheritedClassesInstancesFactory;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.Marker;
import org.knowm.xchart.style.markers.SeriesMarkers;
import processors.methods.MethodProcessor;
import util.Utils;

import java.io.IOException;
import java.util.List;

public class FindExtremaApplication {
    private static final AllInheritedClassesInstancesFactory factory = new AllInheritedClassesInstancesFactory();

    private static final List<UniVariableRealFunctionExtremaFinder> extremaFindersFinders = factory
            .getClassesInstancesBySuperClassOrInterface(UniVariableRealFunctionExtremaFinder.class);

    private static final List<MethodProcessor> processors = factory.getClassesInstancesBySuperClassOrInterface(MethodProcessor.class);

    public static void run(
            UniVariableRealFunction function,
            double startOfSegment,
            double endOfSegment,
            double epsilon
    ) {
        double[] arrayOfArguments = Utils.getArrayOfArguments(startOfSegment, endOfSegment, 0.001);
        XYChart chart = new XYChartBuilder()
                .width(900)
                .height(600)
                .title("")
                .theme(Styler.ChartTheme.Matlab)
                .xAxisTitle("X")
                .yAxisTitle("Y")
                .build();
        chart.addSeries("x^2 * log(x)",
                arrayOfArguments,
                Utils.getArrayOfValuesOfUniVariableRealFunction(function, arrayOfArguments))
                .setMarker(SeriesMarkers.NONE);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideS);
        chart.getStyler().setZoomEnabled(true);
        chart.getStyler().setCursorEnabled(true);
        new SwingWrapper(chart).displayChart();
        try {
            BitmapEncoder.saveBitmap(chart,
                    "extrema",
                    BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();

        }
        extremaFindersFinders.forEach(extremaFinder -> {
            System.out.println(extremaFinder.getClass().getSimpleName() + ": \n");
            processors.forEach(MethodProcessor::processBeforeMethodInvocation);
            double result = extremaFinder.findExtrema(
                    function,
                    startOfSegment,
                    endOfSegment,
                    epsilon
            );
            processors.forEach(MethodProcessor::processAfterMethodInvocation);
            System.out.println("x = " + result + "\nf(x) = " + function.value(result) + "\n-------------------------------------------------");
        });
    }
}
