package collection_stream;

import static collection_stream.CollectionTestUtils.streamForEach문;

import java.util.LinkedList;
import java.util.Queue;
import org.junit.jupiter.api.Test;

public class LinkedListStreamTest {
    @Test
    void test() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            queue.offer(i);
        }

        System.out.println(queue);

//        forEach문(queue, queue::poll);
//        향상된_for_문(queue, queue::poll, 2);
        streamForEach문(queue, queue::poll);

        System.out.println(queue);
    }
}
