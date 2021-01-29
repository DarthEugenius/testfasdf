import org.apache.commons.math3.util.FastMath;
import running.DifferentialEquationsApplication;

public class ApplicationRunner {

    public static void main(String[] args) {
        DifferentialEquationsApplication.run(
                // dy/dx = f(x, y) =
                (x, y) -> -2 * x * y + x * FastMath.exp(-x * x),

                // x_0 =
                0,

                // y_0 = y(x_0) =
                1,

                // x1 =
                2,

                // h =
                0.1
        );
        

        /*ApproximateApplication.run(
                (x) -> FastMath.sqrt(9 * x - 2) - FastMath.sin( 3* x + FastMath.PI / 3),
                1,
                15,
                20
        );*/

        /*for (int i = 0; i < 1; i++) {
            long startTime = System.nanoTime();
            new LagrangeInterpolation().approximate(
                    x -> FastMath.sqrt(2 * x + 1) - FastMath.sin(5 * x - FastMath.PI / 6),
                    0,
                    5,
                    7
            );
            long benchmarkEndTime = System.nanoTime();
            double executingTime = TimeUnit.NANOSECONDS.toNanos(benchmarkEndTime - startTime);
            System.out.println(executingTime / 1000000000 + " seconds");
        }*/

      /*
      FindExtremaApplication.run(
                x -> x * x * FastMath.log(x),
                0.2,
                0.8,
                1e-4
        );
        */
        /*FindZerosOfUniVariableRealFunctionApplication.run(
                x -> FastMath.exp(x) + FastMath.cos(x),
                -FastMath.PI,
                -FastMath.PI/2,
                1e-5
        );*/

    }
}




