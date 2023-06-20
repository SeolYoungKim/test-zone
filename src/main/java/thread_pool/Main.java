package thread_pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        TimingThreadPool timingThreadPool = new TimingThreadPool(10, 20, 1, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>());

        timingThreadPool.execute(() -> System.out.println("hi"));

        timingThreadPool.shutdown();
    }
}
