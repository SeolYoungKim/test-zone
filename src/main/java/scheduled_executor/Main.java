package scheduled_executor;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("currTime=" + LocalDateTime.now()), 0, 5, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(() -> System.out.println("currTime=" + LocalDateTime.now()), 0, 5, TimeUnit.SECONDS);
    }
}
