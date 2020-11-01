import running.IntegrationApplication;

public class ApplicationRunner {

    public static void main(String[] args) {
        IntegrationApplication.run(10,
                0.0000001,
                x -> Math.pow(x, 3) + 2 * Math.pow(x, 2) + Math.PI / 2,
                0,
                1);
    }
}
