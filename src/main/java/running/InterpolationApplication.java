package running;

import entity.UniVariableRealFunction;
import infrastructure.factories.AllInheritedClassesInstancesFactory;
import interpolation_method.AbstractInterpolator;
import org.knowm.xchart.*;
import org.knowm.xchart.style.markers.SeriesMarkers;
import util.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class InterpolationApplication {
    private static final AllInheritedClassesInstancesFactory factory = new AllInheritedClassesInstancesFactory();
    private static final int AMOUNT_OF_SEGMENTS_PLOTS = 10000;

    private static final List<AbstractInterpolator> INTERPOLATORS = factory
            .getClassesInstancesBySuperClassOrInterface(AbstractInterpolator.class);

    public static void run(
            UniVariableRealFunction function,
            double startOfSegment,
            double endOfSegment,
            int amountOfNodes
    ) {
        INTERPOLATORS.forEach(uniVariableRealFunctionInterpolator -> {
            long startTime = System.nanoTime();
            uniVariableRealFunctionInterpolator.interpolate(
                    function,
                    startOfSegment,
                    endOfSegment,
                    amountOfNodes
            );
            long benchmarkEndTime = System.nanoTime();
            double executingTime = TimeUnit.NANOSECONDS.toNanos(benchmarkEndTime - startTime);
            System.out.println(uniVariableRealFunctionInterpolator.getClass().getSimpleName() + " executing time: " + executingTime / 1000000000 + " seconds");
            uniVariableRealFunctionInterpolator.setDefaultAmountsOfSegments(AMOUNT_OF_SEGMENTS_PLOTS);
            double[] arrayOfArguments = Utils.getArrayOfArguments(startOfSegment, endOfSegment, AMOUNT_OF_SEGMENTS_PLOTS);
            double[] arrayOfValuesOfUniVariableRealFunction = Utils.getArrayOfValuesOfUniVariableRealFunction(
                    function,
                    arrayOfArguments);
            String plotName = "src\\main\\java\\interpolation_method\\"+uniVariableRealFunctionInterpolator.getClass().getSimpleName() + amountOfNodes + "Nodes";
            XYChart chart = new XYChartBuilder()
                    .width(900)
                    .height(600)
                    .title(plotName)
                    .xAxisTitle("X")
                    .yAxisTitle("Y")
                    .build();
            XYSeries originalSeries = chart.addSeries("initial function", arrayOfArguments, arrayOfValuesOfUniVariableRealFunction);
            originalSeries.setMarker(SeriesMarkers.NONE);
            File tableInLatex = new File(plotName + ".txt");
            try {
                tableInLatex.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                FileWriter mWriter = new FileWriter(plotName + ".txt");
                double[] differences = new double[amountOfNodes - 1];
                double sumOfDifferences = 0.0;
                double sumOfMSE = 0.0;
                mWriter.write(LatexOutputUtil.headOfTable);
                for (int i = 0; i < amountOfNodes - 1; i++) {
                    double argument = (uniVariableRealFunctionInterpolator.getNodes()[i] + uniVariableRealFunctionInterpolator.getNodes()[i + 1]) / 2;
                    differences[i] = Math.abs(function.value(argument)
                            - uniVariableRealFunctionInterpolator.resultFunction.value(argument));
                    sumOfDifferences += differences[i];
                    sumOfMSE += differences[i]*differences[i];
                    String currentRow = argument +
                            " & " +
                            function.value(argument) +
                            " & " +
                            uniVariableRealFunctionInterpolator.resultFunction.value(argument) +
                            " & " +
                            differences[i] +
                            "\\\\ \\hline \n";
                    mWriter.write(currentRow);
                }
                mWriter.write(LatexOutputUtil.endOfTable);
                mWriter.write("\\centering\n" +
                        "\\begin{tabular}{|l|l|}\n" +
                        "\\hline\n" +
                        "\\begin{tabular}[c]{@{}l@{}}Среднее значение\\\\модуля разности\\end{tabular} & \\begin{tabular}[c]{@{}l@{}}Среднеквадратическое\\\\отклонение\\end{tabular}  \\\\\n" +
                        "\\hline\n" +
                        sumOfDifferences / (amountOfNodes - 1) +
                        " & " +
                        sumOfMSE / (amountOfNodes - 1)  +
                        "\\\\ \n" +
                        "            \\hline\n" +
                        "        \\end{tabular}"+
                        "\\end{table}");
                mWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            XYSeries interpolatedSeries = chart.addSeries("result of approximation",
                    uniVariableRealFunctionInterpolator.getValuesOfArgument(),
                    uniVariableRealFunctionInterpolator.getValuesOfInterpolatedFunction());
            interpolatedSeries.setMarker(SeriesMarkers.NONE);
            XYSeries nodesSeries = chart.addSeries("nodes",
                    uniVariableRealFunctionInterpolator.getNodes(),
                    uniVariableRealFunctionInterpolator.getValuesOfFunctionInNodes());
            nodesSeries.setMarker(SeriesMarkers.CIRCLE);
            nodesSeries.setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
            chart.getStyler().setZoomEnabled(true);
            chart.getStyler().setMarkerSize(12);
            new SwingWrapper(chart).displayChart();
            try {
                BitmapEncoder.saveBitmap(chart,
                        plotName,
                        BitmapEncoder.BitmapFormat.PNG);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
