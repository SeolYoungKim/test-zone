import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TestFor {
    @Test
    void name() {

        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            sum += i;
        }

        final int[] ints = {1, 2, 2, 3, 34, 13, 41, 24, 12, 4};
        for (int anInt : ints) {
            sum += anInt;
        }

        System.out.println(sum);

        final List<Integer> numbers = List.of(1, 12, 312, 3, 123, 12, 3, 123);
        for (Integer number : numbers) {
            System.out.println(number);
        }
    }

    record TestClass(int a, int b) {
    }
    @Test
    void nam2e() {
        TestClass testClass1 = new TestClass(1, 2);
        TestClass testClass2 = new TestClass(1, 2);

        ArrayList<TestClass> testClasses = new ArrayList<>();
        testClasses.add(testClass1);
        testClasses.add(testClass2);

        testClasses.forEach(tc -> System.out.println("hashCode() = " + tc.hashCode()));
        System.out.println(testClass1 == testClass2);
    }
}
