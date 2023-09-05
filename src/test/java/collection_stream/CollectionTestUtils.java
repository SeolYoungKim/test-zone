package collection_stream;

import java.util.Collection;
import java.util.function.Supplier;

public class CollectionTestUtils {
    private CollectionTestUtils() {
    }

    public static void forEach문(Collection<Integer> collection, Supplier<Integer> supplier) {
        collection.forEach(i -> {
            System.out.println("current number=" + i);
            System.out.println("remove number=" + supplier.get());
        });
    }

    public static void streamForEach문(Collection<Integer> collection, Supplier<Integer> supplier) {
        collection.forEach(i -> {
            System.out.println("current number=" + i);
            System.out.println("remove number=" + supplier.get());
        });
    }

    public static void 향상된_for_문(Collection<Integer> collection, Supplier<Integer> supplier, Integer boundaryNum) {
        for (Integer num : collection) {
            System.out.println("current number=" + num);
            System.out.println("remove number=" + supplier.get());

            if (num == boundaryNum) {
                break;
            }
        }
    }
}
