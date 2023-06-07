package async.future_task.cancel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        final Future<?> future = executorService.submit(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("작업이 수행되었습니다.");
            } catch (InterruptedException ignored) {
            }
        });

        Thread.sleep(1000);

        // 작업 중단 시도. 결과값은 취소 요청에 따른 작업 중단 시도가 성공적이었는지를 알려줌.
        // 성공: 인터럽트를 제대로 걸었따는 의미. 인터럽트에 반응해 실제로 작업을 중단하였음.
        // true: 작업이 스레드에서 실행되고 있는 경우, 해당 스레드에 인터럽트를 검 | false: 아직 실행하지 않았다면 실행시키지 말아라 라는 의미
        // 인터럽트를 대응하지 않도록 만들어진 경우, 항상 false를 넘겨줘야 함
        // 스레드에 직접 인터럽트를 걸기 보다는, 인터럽트 정책이 마련된 ExecutorService를 이용하는 편이 좋다.
        // - Future의 cancel 메서드를 이용하자.
        final boolean cancel = future.cancel(true);
        System.out.println(cancel);

        executorService.shutdown();
    }
}
