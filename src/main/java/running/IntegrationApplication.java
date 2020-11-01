package running;

import entity.UniVariableRealFunction;
import entity.UniVariableRealFunctionIntegrator;
import infrastructure.factories.AllInheritedClassesInstancesFactory;
import processors.methods.MethodProcessor;

import java.util.List;

/// FIXME: 02.11.2020 Убрать овнокод
public class IntegrationApplication {

    private static final AllInheritedClassesInstancesFactory factory = new AllInheritedClassesInstancesFactory();

    private static final List<UniVariableRealFunctionIntegrator> integrators = factory
            .getClassesInstancesBySuperClassOrInterface(UniVariableRealFunctionIntegrator.class);

    private static final List<MethodProcessor> processors = factory.getClassesInstancesBySuperClassOrInterface(MethodProcessor.class);

    public static void run(int iterations,
                           UniVariableRealFunction function,
                           double integrateFrom,
                           double integrateTo) {
        integrators.forEach(integrator -> {
            processors.forEach(MethodProcessor::processBeforeMethodInvocation);
                System.out.println("Implementation class: " + integrator.getClass());
                System.out.println("Integral value: " + integrator.integrate(iterations, function, integrateFrom, integrateTo));
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

    public static void run(int iterations,
                           double accuracy,
                           UniVariableRealFunction function,
                           double integrateFrom,
                           double integrateTo) {
        integrators.forEach(integrator -> {
            processors.forEach(MethodProcessor::processBeforeMethodInvocation);
            System.out.println("Implementation class: " + integrator.getClass());
            System.out.println("Integral value: " + integrator.integrate(iterations, accuracy, function, integrateFrom, integrateTo));
            processors.forEach(MethodProcessor::processAfterMethodInvocation);
            System.out.println();
        });
    }
}