package countdown_latch;

import java.util.concurrent.CountDownLatch;

public class TestHarness {
    public long timeTasks(int nThreads, Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        System.out.println("[main] Thread 생성 작업 시작");

        for (int i = 0; i < nThreads; i++) {
            final Thread thread = new Thread(() -> {
                System.out.println("[" + Thread.currentThread().getName() + "] START");
                try {
                    startGate.await();  // main 스레드가 startGate의 countDown()을 호출할 때까지 대기
                    try {
                        System.out.println("[" + Thread.currentThread().getName() + "] Run task");
                        task.run();
                    } finally {
                        endGate.countDown();
                    }
                } catch (InterruptedException ignored) {
                }
            });

            thread.start();  // 시작은 했지만 startGate.await()로 인해 대기중이다. 해당 카운트다운 래치의 카운트가 0이되어야 비로소 다음 로직들을 실행한다.
        }

        System.out.println("[main] Thread 생성 작업 종료");

        final long start = System.nanoTime();
        startGate.countDown();  // startGate.await()에서 대기중인 스레드들이 동시시작된다.
        endGate.await();  // main 스레드는 다른 모든 스레드들이 endGate.countDown()을 호출하여 endGate의 count가 0이 될 때까지 기다린다.
        final long end = System.nanoTime();

        return end - start;  // 걸린 시간을 잰다.
    }

    public static void main(String[] args) throws InterruptedException {
        final TestHarness testHarness = new TestHarness();
        final long result = testHarness.timeTasks(10, () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
        });

        System.out.println("RESULT=" + result);
    }
}
