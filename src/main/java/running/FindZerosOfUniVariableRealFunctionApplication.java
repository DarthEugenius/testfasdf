package running;

import entity.UniVariableRealFunction;
import entity.UniVariableRealFunctionZeroFinder;
import find_zeros_method.DichotomyMethod;
import infrastructure.factories.AllInheritedClassesInstancesFactory;
import processors.methods.MethodProcessor;

import java.util.ArrayList;
import java.util.List;

public class FindZerosOfUniVariableRealFunctionApplication {

    private static final AllInheritedClassesInstancesFactory factory = new AllInheritedClassesInstancesFactory();

    private static final List<UniVariableRealFunctionZeroFinder> zeroOfFunctionFinders = factory
            .getClassesInstancesBySuperClassOrInterface(UniVariableRealFunctionZeroFinder.class);

    private static final List<MethodProcessor> processors = factory.getClassesInstancesBySuperClassOrInterface(MethodProcessor.class);





    public static void run(
            UniVariableRealFunction function,
            double startOfSegment,
            double endOfSegment,
            double epsilon) {
        zeroOfFunctionFinders.forEach(
                zeroOfFunctionFinder -> {
                    System.out.println("\nImplementation class: " + zeroOfFunctionFinder.getClass());
                    processors.forEach(MethodProcessor::processBeforeMethodInvocation);
                    double result = zeroOfFunctionFinder.findZero(function, startOfSegment, endOfSegment, epsilon);
                    processors.forEach(MethodProcessor::processAfterMethodInvocation);
                    System.out.println(result);
                }
        );
    }
}
