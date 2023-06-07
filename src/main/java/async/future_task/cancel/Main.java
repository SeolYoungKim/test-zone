package async.future_task.cancel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        final Future<?> future = executorService.submit(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("작업이 수행되었습니다.");
            } catch (InterruptedException ignored) {
            }
        });

        Thread.sleep(1000);
        final boolean cancel = future.cancel(true);
        System.out.println(cancel);

        executorService.shutdown();
    }
}
