package generic;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenericTest<T> {
    private T t;

    public static void main(String[] args) {
        GenericTest<String> genericTest1 = new GenericTest<>();
        GenericTest<Integer> genericTest2 = new GenericTest<>();
        GenericTest<Boolean> genericTest3 = new GenericTest<>();

        genericTest1.setT("Hello");
        // 컴파일 에러
//        genericTest1.setT(1);
//        genericTest1.setT(true);

        genericTest2.setT(1);
        // 컴파일 에러
//        genericTest2.setT("Hello");
//        genericTest2.setT(true);

        genericTest3.setT(true);
        // 컴파일 에러
//        genericTest3.setT("Hello");
//        genericTest3.setT(1);

        List<String> strings = new ArrayList<>();
        strings.add("Hello");
        // 컴파일 에러
//        strings.add(1);
//        strings.add(true);
    }
}
