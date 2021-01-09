package running;

import com.sun.prism.BasicStroke;
import entity.CauchyProblemSolver;
import entity.TwoVariablesRealFunction;
import infrastructure.factories.AllInheritedClassesInstancesFactory;
import org.apache.commons.math3.util.FastMath;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.Marker;
import org.knowm.xchart.style.markers.SeriesMarkers;
import util.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DifferentialEquationsApplication {
    private static final AllInheritedClassesInstancesFactory FACTORY = new AllInheritedClassesInstancesFactory();

    private static final List<CauchyProblemSolver> CAUCHY_PROBLEM_SOLVERS = FACTORY
            .getClassesInstancesBySuperClassOrInterface(CauchyProblemSolver.class);

    public static void run(
            TwoVariablesRealFunction derivative,
            double startOfSegment,
            double valueOfFunctionInStartOfSegment,
            double endOfSegment,
            double step) {

        XYChart chart = new XYChartBuilder()
                .width(900)
                .height(600)
                .title("Решение дифференциального уравнения на интервале [" +
                        startOfSegment +
                        ";" +
                        endOfSegment +
                        "]; y(" +
                        startOfSegment +
                        ") = " +
                        valueOfFunctionInStartOfSegment +
                        "; шаг:" +
                        step)
                .theme(Styler.ChartTheme.Matlab)
                .xAxisTitle("X")
                .yAxisTitle("Y")
                .build();
        double[] arrayOfArguments = Utils.getArrayOfArguments(startOfSegment, endOfSegment, step);
        CAUCHY_PROBLEM_SOLVERS.forEach(
                cauchyProblemSolver -> {
                    long startTime = System.nanoTime();
                    double[] solution = cauchyProblemSolver.solve(
                            derivative,
                            startOfSegment,
                            valueOfFunctionInStartOfSegment,
                            endOfSegment,
                            step
                    );
                    long benchmarkEndTime = System.nanoTime();
                    double executingTime = TimeUnit.NANOSECONDS.toNanos(benchmarkEndTime - startTime);
                    double[] solutionWithBiggerStep = cauchyProblemSolver.solve(
                            derivative,
                            startOfSegment,
                            valueOfFunctionInStartOfSegment,
                            endOfSegment,
                            step * 2
                    );
                    double errorOnStep = 0.0;
                    for (int i = 0; i < solutionWithBiggerStep.length; i++) {
                        errorOnStep += FastMath.abs(solutionWithBiggerStep[i] - solution[2 * i]);
                    }
                    errorOnStep /= FastMath.pow(2, cauchyProblemSolver.getOrder()) - 1;
                    System.out.println(cauchyProblemSolver.toString() + "\nexecuting time: " + executingTime / 1000000000 + " seconds\n");
                    XYSeries currentSeries = chart.addSeries(
                            cauchyProblemSolver.toString(),
                            arrayOfArguments,
                            solution);
                    currentSeries.setMarker(SeriesMarkers.NONE);
                    currentSeries.setSmooth(false);
                    File tableInLatex = new File(cauchyProblemSolver.toString() + ".txt");
                    try {
                        tableInLatex.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        FileWriter mWriter = new FileWriter(cauchyProblemSolver.toString() + ".txt");

                        mWriter.write(DiffLatexOutputUtil.headOfTable);
                        for (int i = 0; i < arrayOfArguments.length; i++) {
                            String currentRow = String.format("%.3f & %.16f \\\\ \\hline\n", arrayOfArguments[i], solution[i]);
                            mWriter.write(currentRow);
                        }
                        mWriter.write(DiffLatexOutputUtil.endOfTable);
                        mWriter.write(String.format("\n\\newline \\begin{center}Погрешность на шаге: %.2e\\end{center}", errorOnStep));
                        mWriter.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

        );
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideS);
        chart.getStyler().setZoomEnabled(true);
        chart.getStyler().setCursorEnabled(true);
        new SwingWrapper(chart).displayChart();
        try {
            BitmapEncoder.saveBitmap(chart,
                    "differentialEquationSolutions",
                    BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();

        }




    }
    class DiffLatexOutputUtil {
        public static final String headOfTable = "\\begin{table}[H]\n" +
                "        \\centering\n" +
                "        \\begin{tabular}{|l|l|}\n" +
                "            \\hline\n" +
                "            \\multicolumn{1}{|c|}{x} & \\multicolumn{1}{c|}{\\begin{tabular}[c]{@{}c@{}}Численное решение \\\\в точке x\\end{tabular}} \\\\\n" +
                "            \\hline";
        public static final String endOfTable = "\\end{tabular}\n" +
                "    \\end{table}";

    }
}
