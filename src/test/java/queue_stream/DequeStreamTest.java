package queue_stream;

import java.util.ArrayDeque;
import java.util.Deque;
import org.junit.jupiter.api.Test;

public class DequeStreamTest {
    @Test
    void test() {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            deque.addLast(i);
        }

        System.out.println(deque);

//        forEach문(deque);
//        향상된_for_문(deque);
        streamForEach문(deque);

        System.out.println(deque);
    }

    /**
     * 모든 작업이 수행된 후 ConcurrentModificationException 발생
     * - 즉, deque 순회도 끝까지 돌고, deque의 요소도 끝까지 제거
     */
    private static void forEach문(Deque<Integer> deque) {
        deque.forEach(i -> {
                    System.out.println("current number=" + i);
                    System.out.println("deque.removeLast() = " + deque.removeLast());
                });
    }

    /**
     * 모든 작업이 수행된 후 ConcurrentModificationException 발생
     * - 즉, deque 순회도 끝까지 돌고, deque의 요소도 끝까지 제거
     */
    private static void streamForEach문(Deque<Integer> deque) {
        deque.forEach(i -> {
            System.out.println("current number=" + i);
            System.out.println("deque.removeLast() = " + deque.removeLast());
        });
    }

    /**
     * 다음 요소가 없을 때 ConcurrentModificationException 발생
     * - 다만, 다음 요소가 없다고 판단되기 전에 break문으로 탈출 가능
     */
    private static void 향상된_for_문(Deque<Integer> deque) {
        for (Integer num : deque) {
            System.out.println("current number=" + num);
            System.out.println("deque.removeLast() = " + deque.removeLast());

            if (num == 2) {
                break;
            }
        }
    }
}
