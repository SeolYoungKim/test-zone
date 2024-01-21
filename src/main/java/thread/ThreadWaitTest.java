package thread;

public class ThreadWaitTest {
    public static void main(String[] args) throws InterruptedException {
        // 1. 스레드를 만든다.
        MyThread myThread = new MyThread();

        // 2. 스레드를 실행한다.
        myThread.start();

        // 3. 메인 스레드가 그냥 대기하는 메서드를 실행한다.
        //  - 메인 스레드가 MyThread의 락을 쥐고 아래의 메서드가 끝날 때 까지 기다린다. (블로킹된다고 볼 수 있다..?)
        myThread.그냥_대기하는_메서드();

        // 4. 인터럽트를 호출 해본다.
        //  - 이는 위 메서드가 끝날 때 까지 호출이 불가능하다.
        //  - main 스레드는 myThread.그냥_대기하는_메서드() 가 끝나야만 여기로 넘어올 수 있다.
        myThread.interrupt();
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread run");
        }

        public synchronized void 그냥_대기하는_메서드() throws InterruptedException {
            while (true) {
                System.out.println("[" + Thread.currentThread().getName() + "]" + " 대기중...");
                Thread.sleep(5000);
            }
        }
    }
}
