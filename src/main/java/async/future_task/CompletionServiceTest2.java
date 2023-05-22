package async.future_task;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionServiceTest2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final ExecutorService executorService = Executors.newFixedThreadPool(100);

        final List<Future<UUID>> futures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            futures.add(executorService.submit(CompletionServiceTest2::makeUUID));
        }

        for (Future<UUID> future : futures) {
            System.out.println("get: " + future.get());
        }
    }

    public static UUID makeUUID() {
        System.out.println("[" + Thread.currentThread().getName() + "]" + "UUID가 발급되었습니다.");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException ignored) {}

        return UUID.randomUUID();
    }
}
