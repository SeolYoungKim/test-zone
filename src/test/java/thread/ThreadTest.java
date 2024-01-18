package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ThreadTest {
    @DisplayName("Thread 안에 Thread를 두면 어떻게 될까? => 스레드 start를 하지 않기 때문에 일반적인 Runnable처럼 동작한다.")
    @Test
    void threadInThread() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        // 외부스레드.start() -> start가 해당 스레드의 run()을 실행 -> 내부스레드.run() -> 최종적으로 사용되는 스레드는 2개밖에 안되나?
        //  -> ㅇㅇ 내부 스레드의 run만 호출하기 때문에 그 생각이 맞음. 실제로 아래의 코드를 보면 내부 스레드는 주거이따.
        String testMainThread = Thread.currentThread().getName();
        System.out.println("testMainThread = " + testMainThread);

        Thread internalThread = new Thread(() -> {
            countDownLatch.countDown();
            System.out.println("이걸 실행시킨 스레드는 과연 누구? -> " + Thread.currentThread().getName());
            System.out.println("이걸 실행시킨 스레드는 살아있냐? -> " + Thread.currentThread().isAlive());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("internalThread = " + internalThread.getName());
        System.out.println("internalThread.isAlive() = " + internalThread.isAlive());

        Thread externalThread = new Thread(internalThread);
        System.out.println("externalThread = " + externalThread.getName());

        externalThread.start();
        countDownLatch.await();

        System.out.println("internalThread.isAlive() = " + internalThread.isAlive());
    }

    @Test
    void thread() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        ExecutorService threadPool1 = Executors.newFixedThreadPool(10);
        ExecutorService threadPool2 = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            threadPool1.execute(() -> {
                try {
                    System.out.println("현재스레드 = " + Thread.currentThread().getName());
                    Thread.sleep(5000);
                    countDownLatch.countDown();  // 요게 없으면, 스레드가 잠든 채로 메인 스레드가 종료돼서
                    System.out.println("일어남");  // 이게 출력이 안됨. 근데 이건 테스트에서만 그런거고... 메인 메서드에서 실행하면 또 다르다. 없어도 됨.
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

        countDownLatch.await();
    }

    @DisplayName("테스트 코드에선 이 모든걸 걍 끝내버린다. main에서 테스트하면 아래 메서드들은 무한루프를 탄다.")
    @Test
    void infiniteLoopInThread() {
        new Thread(() -> {
            while (true) {
                System.out.println("현재스레드 = " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new InfiniteLoopThread().start();

    }

    static class InfiniteLoopThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("현재스레드 = " + Thread.currentThread().getName());
                    Thread.sleep(1000);  // 스레드를 재워버려서 while문이 필요 없지 않나.. 수행중에 현재 스레드를 재우는데...
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
