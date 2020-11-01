package processors.methods.procs;

import processors.methods.MethodProcessor;

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
        System.out.println("Benchmark time: " + (benchmarkEndTime - this.startTime.get()));
    }
}
