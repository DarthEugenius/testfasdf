import entity.UniVariableRealFunction;
import running.DifferentialEquationsApplication;

public class ApplicationRunner {

    public static void main(String[] args) {


        DifferentialEquationsApplication.run(
                (x, y) -> 3 * Math.exp(-x) - 2 * Math.pow(y, 2),
                0,
                1,
                0.5,
                0.01
        );




















        /* UniVariableRealFunction function = x -> Math.sqrt(9 * x - 2) + Math.sin(3 * x + Math.PI/3);*//*
        UniVariableRealFunction function = x -> Math.asin(2 * x - 1);
        InterpolationApplication.run(
                function,
                0,
                1,
                8
        );
*/

        /* FindZerosOfUniVariableRealFunctionApplication.run(
                x -> x * (x - 4) * (x - 3),
                -2,
                1,
                1e-10
        );*/

    }
}




