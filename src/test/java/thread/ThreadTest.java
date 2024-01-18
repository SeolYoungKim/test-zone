package thread;

import java.util.concurrent.CountDownLatch;
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
}
