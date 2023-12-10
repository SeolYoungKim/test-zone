package passbyvalue_and_passbyreference;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PassByValueAndReferenceTest {
    @Test
    void test() {
        callPassByValueAndReference();
    }

    public void callPassByValueAndReference() {
        int a = 10;
        String b = "b";
        List<String> c = new ArrayList<>();
        c.add("c");

        System.out.println("before passByValue");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);

        passByValueAndReference(a, b, c);

        System.out.println("after passByValue");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
    }

    /**
     * 기본 자료형은 값이 복사되어 전달되고, 참조 자료형은 주소값이 전달된다.
     * - 기본 자료형 → Pass By Value
     * - 참조 자료형 → Pass By Reference
     */
    private void passByValueAndReference(int a, String b, List<String> c) {  // a = 10, b = "b", c = "List<String> c 의 주소값"
        a = 20;
        b = "z";

        c.add("d");  // add는 적용된다. 파라미터로 넘어온 c의 주소 값을 가지고 있고, 거기에 자료를 더하는것이기 때문.
        c = new ArrayList<>();  // 하지만 재할당을 한다고 해서 외부의 c라는 참조 변수에 영향을 주지는 않는다.

        System.out.println("in passByValue");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
    }
}
