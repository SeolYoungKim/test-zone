package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MonitorSleepTest {
    public static void main(String[] args) {
        LockObject lockObject = new LockObject();

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                try {
                    lockObject.sleepJust10Minutes();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        threadPool.shutdown();
    }

    // Minutes = 분
    static class LockObject {
        // Thread.sleep을 호출한다고 해서 락이 해제되고 잠드는게 아님
        // 락을 쥐고 잠들어버림;
        public synchronized void sleepJust10Minutes() throws InterruptedException {
            Thread.sleep(5000);
            System.out.println("[" + Thread.currentThread().getName() + "]" + " 일어남");
        }
    }
}
