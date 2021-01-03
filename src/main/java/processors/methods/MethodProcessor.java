package processors.methods;

import processors.Processor;

/**
 * A root interface for method handling. For example: benchmark, profiling, scheduling, e.t.c.
 *
 * @version 1.0
 */
public interface MethodProcessor extends Processor {

    void processBeforeMethodInvocation();

    void processAfterMethodInvocation();

}
