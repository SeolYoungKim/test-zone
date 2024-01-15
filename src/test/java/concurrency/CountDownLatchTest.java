package concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 이펙티브 자바 3판 Item 81에 있는 예제임
 */
public class CountDownLatchTest {
    @DisplayName("CountDownLatch 활용 방법")
    @Test
    void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        try {
            long time = time(executorService, 10, () -> System.out.println("[" + Thread.currentThread().getName() + "] 작업 실행"));
            System.out.println("경과 시간: " + time + " ns");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    public long time(Executor executor, int concurrency, Runnable action) throws InterruptedException {
        CountDownLatch ready = new CountDownLatch(concurrency);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(concurrency);

        for (int i = 0; i < concurrency; i++) {  // 1. [main 스레드] for문 실행
            executor.execute(() -> {  // 2. [main 스레드] concurrency 만큼의 작업을 제출
                ready.countDown();  // 3. [작업에 참여하는 다른 스레드] ready 카운트를 하나 줄임
                try {
                    start.await();  // 4. [작업에 참여하는 다른 스레드] start 카운트가 0이 될 때까지 기다림
                    action.run();  // 9. [작업에 참여하는 다른 스레드] 작업 실행
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    done.countDown();  // 10. [작업에 참여하는 다른 스레드] done 카운트를 하나 줄임
                }
            });
        }

        ready.await();  // 5. [main 스레드] 모든 작업자가 준비될 때까지 기다림
        long startNanos = System.nanoTime();  // 6. [main 스레드] 모든 작업자가 준비되면(ready 카운트를 0으로 만들면) 시작 시간을 기록
        start.countDown();  // 7. [main 스레드] start 카운트를 0으로 만듦
        done.await();  // 8. [main 스레드] 모든 작업자가 작업을 끝마칠 때까지 기다림

        return System.nanoTime() - startNanos;  // 11. [main 스레드] 모든 작업자가 작업을 끝마치면(done 카운트를 0으로 만들면) 경과 시간을 반환
    }
}
