package async.semaphore;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

public class BoundedHashSet<T> {
    private final Set<T> set;
    private final Semaphore sem;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<>());
        this.sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        sem.acquire();  // 스레드가 bound 개수 만큼만 동시 진입할 수 있음
        System.out.println("[" + Thread.currentThread().getName() + "] was acquired");
        boolean wasAdded = false;

        try {
            wasAdded = set.add(o);
            return wasAdded;
        } finally {
            if (!wasAdded) {
                Thread.sleep(5000);  //릴리즈 전 5초 대기

                sem.release();
                System.out.println("[" + Thread.currentThread().getName() + "] was released");
            }
        }
    }

    public boolean remove(T o) {
        final boolean wasRemoved = set.remove(o);
        if (wasRemoved) {
            sem.release();
        }
        return wasRemoved;
    }

    public static void main(String[] args) {
        final BoundedHashSet<Integer> objectBoundedHashSet = new BoundedHashSet<>(5);
        final ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> {
                try {
                    objectBoundedHashSet.add(1);
                } catch (InterruptedException ignored) {
                }
            });
        }

    }
}
