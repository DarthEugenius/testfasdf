package running;

import entity.UniVariableRealFunctionIntegrator;
import infrastructure.factories.AllInheritedClassesInstancesFactory;
import processors.methods.MethodProcessor;

import java.util.List;

public class IntegrationApplication {

    public static void run() {
        final AllInheritedClassesInstancesFactory factory = new AllInheritedClassesInstancesFactory();

        List<UniVariableRealFunctionIntegrator> integrators = factory
                .getClassesInstancesBySuperClassOrInterface(UniVariableRealFunctionIntegrator.class);

        List<MethodProcessor> processors = factory.getClassesInstancesBySuperClassOrInterface(MethodProcessor.class);

        integrators.forEach(integrator -> {
            processors.forEach(MethodProcessor::processBeforeMethodInvocation);
                System.out.println("Implementation class: " + integrator.getClass());
                System.out.println("Integral value: " + integrator.integrate(10, 0.0000001, Math::cos, 0, 1));
            processors.forEach(MethodProcessor::processAfterMethodInvocation);
            System.out.println();

        });
    }
}