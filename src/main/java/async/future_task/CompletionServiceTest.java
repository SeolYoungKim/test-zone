package async.future_task;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CompletionServiceTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final ExecutorCompletionService<UUID> executorCompletionService = new ExecutorCompletionService<>(
                Executors.newFixedThreadPool(100));

        for (int i = 0; i < 100; i++) {
            executorCompletionService.submit(CompletionServiceTest::makeUUID);
        }

        for (int i = 0; i < 50; i++) {
            final Future<UUID> take = executorCompletionService.take();
            System.out.println("take: " + take.get());
        }

        for (int i = 0; i < 50; i++) {
            final Future<UUID> poll = executorCompletionService.poll(10, TimeUnit.SECONDS);
            System.out.println("poll: " + poll.get());
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
