import org.apache.commons.math3.util.FastMath;
import running.ApproximateApplication;

public class ApplicationRunner {

    public static void main(String[] args) {
        /*
        DifferentialEquationsApplication.run(
                // dy/dx = f(x, y) =
                (x, y) -> x * x - y * y,

                // x_0 =
                0,

                // y_0 = y(x_0) =
                1,

                // x1 =
                0.5,

                // h =
                0.01
        );
        */

        ApproximateApplication.run(
                (x) -> FastMath.sqrt(2 * x + 1) - FastMath.sin(5 * x - FastMath.PI / 6),
                -0.5,
                5,
                7
        );

        /*
        for (int i = 0; i < 1; i++) {
            long startTime = System.nanoTime();
            new LagrangeInterpolation().interpolate(
                    x -> FastMath.sqrt(2 * x + 1) - FastMath.sin(5 * x - FastMath.PI / 6),
                    0,
                    5,
                    7
            );
            long benchmarkEndTime = System.nanoTime();
            double executingTime = TimeUnit.NANOSECONDS.toNanos(benchmarkEndTime - startTime);
            System.out.println(executingTime / 1000000000 + " seconds");
        }
      */

      /*
      FindExtremaApplication.run(
                x -> x * x * FastMath.log(x),
                0.2,
                0.8,
                1e-4
        );
        */

    }
}




