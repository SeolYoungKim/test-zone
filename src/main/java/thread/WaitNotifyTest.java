package thread;

public class WaitNotifyTest {
    static class MyThread extends Thread {
        private final Object lock;

        public MyThread(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            System.out.println("MyThread run");
            try {
                for (int i = 0; i < 10000; i++) {
                    String a = "A";
                }

                synchronized (lock) {
                    lock.wait();  // lock의 모니터를 놓고, 깨어나면 다시 잡는다.
                }

                System.out.println("[" + getName() + "] notified");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Object lock = new Object();
        MyThread myThread = new MyThread(lock);

        try {
            System.out.println("thread state=" + myThread.getState());
            myThread.start();  // 메인 스레드가 myThread를 실행시키면, myThread 내부에서 lock.wait()을 호출하여, myThread가 대기 상태가 된다.
            System.out.println("thread state(after start)=" + myThread.getState());  // 메인 스레드가 myThread의 상태 확인

            Thread.sleep(100);
            System.out.println("thread state(after 0.1sec)=" + myThread.getState());  // 메인 스레드가 myThread의 상태 확인

            synchronized (lock) {  // 메인 스레드가 lock의 모니터를 획득 (myThread 내부에서 wait()을 호출하여 락을 놓아야 가능)
                lock.notify();  // lock의 모니터에서 대기중인 myThread를 깨운다.
            }
            Thread.sleep(100);
            System.out.println("thread state(after notify)=" + myThread.getState());  // myThread가 깨어나고 나머지 작업들이 수행된다

            myThread.join();
            System.out.println("thread state(after join)=" + myThread.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
