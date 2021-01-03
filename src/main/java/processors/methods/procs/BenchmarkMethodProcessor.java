package processors.methods.procs;

import processors.methods.MethodProcessor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class BenchmarkMethodProcessor implements MethodProcessor {

    private AtomicLong startTime;

    @Override
    public void processBeforeMethodInvocation() {
        this.startTime = new AtomicLong(System.nanoTime());
    }

    @Override
    public void processAfterMethodInvocation() {
        long benchmarkEndTime = System.nanoTime();
        double executingTime = TimeUnit.NANOSECONDS.toNanos(benchmarkEndTime - this.startTime.get());
        System.out.println("Executing time: " + executingTime / 1000000000 + " seconds");

    }


}
