package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool1 = Executors.newFixedThreadPool(10);
        ExecutorService threadPool2 = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            threadPool1.execute(() -> {
                try {
                    System.out.println("현재스레드 = " + Thread.currentThread().getName());
                    Thread.sleep(5000);
                    System.out.println("일어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            threadPool2.execute(() -> {
                System.out.println("현재스레드 = " + Thread.currentThread().getName());
            });
        }

        Thread thread = Thread.currentThread();
        ThreadGroup threadGroup = thread.getThreadGroup();
        System.out.println("메인스레드 = " + thread.getName());
        System.out.println("메인스레드그룹 = " + threadGroup.getName());

        ThreadGroup parent = threadGroup.getParent();
        System.out.println("메인스레드그룹의 부모 = " + parent.getName());
        System.out.println("메인스레드그룹의 부모의 부모 = " + parent.getParent());

        new InfiniteLoopThread().start();
    }

    static class InfiniteLoopThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("현재스레드 = " + Thread.currentThread().getName());
                    System.out.println(System.nanoTime());
                    Thread.sleep(1000);  // 스레드를 재워버려서 while문이 필요 없지 않나.. 수행중에 현재 스레드를 재우는데...
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
