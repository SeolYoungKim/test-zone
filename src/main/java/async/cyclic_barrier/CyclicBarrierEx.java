package async.cyclic_barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierEx {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        final CyclicBarrier cyclicBarrier1 = new CyclicBarrier(5,
                () -> System.out.println("Cyclic Barrier1 broken by [" + Thread.currentThread().getName() + "]"));

        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                try {
                    cyclicBarrier1.await();
                } catch (InterruptedException | BrokenBarrierException ignored) {
                }
            });
        }

        final CyclicBarrier cyclicBarrier2 = new CyclicBarrier(10,
                () -> System.out.println("Cyclic Barrier2 broken by [" + Thread.currentThread().getName() + "]"));


        for (int i = 0; i < 20; i++) { // 사이클릭 배리어는 재사용할 수 있다. (2번이나 사용되는 것을 볼 수 있음.)
            executorService.submit(() -> {
                try {
                    cyclicBarrier2.await();
                } catch (InterruptedException | BrokenBarrierException ignored) {
                }
            });
        }

        final CyclicBarrier cyclicBarrier3 = new CyclicBarrier(15,
                () -> System.out.println("Cyclic Barrier3 broken by [" + Thread.currentThread().getName() + "]"));


        for (int i = 0; i < 15; i++) {
            executorService.submit(() -> {
                try {
                    cyclicBarrier3.await();  // 스레드 풀에는 10개의 스레드밖에 없으므로 배리어가 깨지지 않아, 배리어가 끝났다는 메세지가 뜨지 않는다.
                } catch (InterruptedException | BrokenBarrierException ignored) {
                }
            });
        }
    }
}
