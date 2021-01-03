package running;

import entity.UniVariableRealFunction;
import entity.UniVariableRealFunctionIntegrator;
import infrastructure.factories.AllInheritedClassesInstancesFactory;
import processors.methods.MethodProcessor;

import java.util.List;

/// FIXME pls
public class IntegrationApplication {

    private static final AllInheritedClassesInstancesFactory factory = new AllInheritedClassesInstancesFactory();

    private static final List<UniVariableRealFunctionIntegrator> integrators = factory
            .getClassesInstancesBySuperClassOrInterface(UniVariableRealFunctionIntegrator.class);

    private static final List<MethodProcessor> processors = factory.getClassesInstancesBySuperClassOrInterface(MethodProcessor.class);

    public static void run(int segments,
                           UniVariableRealFunction function,
                           double integrateFrom,
                           double integrateTo) {
        double INTEGRAL_VALUE = 2.487462993461564;
        integrators.forEach(integrator -> {
            System.out.println("Implementation class: " + integrator.getClass());

            processors.forEach(MethodProcessor::processBeforeMethodInvocation);
            double firstResult = integrator.integrate(segments, function, integrateFrom, integrateTo);
            System.out.printf("Integral value(%d segments): %.20f \n", segments, firstResult);
            System.out.printf("error: %.20f \n", Math.abs(firstResult-INTEGRAL_VALUE));
            processors.forEach(MethodProcessor::processAfterMethodInvocation);

            processors.forEach(MethodProcessor::processBeforeMethodInvocation);
            double secondResult = integrator.integrate(segments * 2, function, integrateFrom, integrateTo);
            System.out.printf("Integral value(%d segments): %.20f \n", segments * 2, secondResult);
            System.out.printf("error: %.20f \n", Math.abs(secondResult-INTEGRAL_VALUE));
            processors.forEach(MethodProcessor::processAfterMethodInvocation);

            processors.forEach(MethodProcessor::processBeforeMethodInvocation);
            double thirdResult = integrator.integrate(segments * 4, function, integrateFrom, integrateTo);
            System.out.printf("Integral value(%d segments): %.20f \n", segments * 4, thirdResult);
            System.out.printf("error: %.20f \n", Math.abs(thirdResult-INTEGRAL_VALUE));
            processors.forEach(MethodProcessor::processAfterMethodInvocation);

            System.out.println();

        });
    }

    public static void run(double accuracy,
                           UniVariableRealFunction function,
                           double integrateFrom,
                           double integrateTo) {
        integrators.forEach(integrator -> {
            processors.forEach(MethodProcessor::processBeforeMethodInvocation);
            System.out.println("Implementation class: " + integrator.getClass());
            System.out.println("Integral value: " + integrator.integrate(accuracy, function, integrateFrom, integrateTo));
            processors.forEach(MethodProcessor::processAfterMethodInvocation);
            System.out.println();
        });
    }

    public static void run(int segments,
                           double accuracy,
                           UniVariableRealFunction function,
                           double integrateFrom,
                           double integrateTo) {
        integrators.forEach(integrator -> {
            processors.forEach(MethodProcessor::processBeforeMethodInvocation);
            System.out.println("Implementation class: " + integrator.getClass());
            System.out.println("Integral value: " + integrator.integrate(segments, accuracy, function, integrateFrom, integrateTo));
            processors.forEach(MethodProcessor::processAfterMethodInvocation);
            System.out.println();
        });
    }
}