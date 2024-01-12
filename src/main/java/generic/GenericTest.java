package generic;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenericTest<T> {
    private T t;

    public void print(List<T> list) {
        System.out.println(list);
    }

    public void printWildCardList(List<?> list) {
        System.out.println(list);
    }

    public void printNumberList(List<? extends Number> list) {
        System.out.println(list);
    }

    // 제네릭 메서드의 제네릭 선언은 메서드의 반환 타입 앞에 위치한다.
    // 그래서 extends 선언은 선언부에 작성해야 한다. 파라미터는 선언된 제네릭을 사용하는 부분이다.
    public <E extends Number> void printOtherGeneric(List<E> e) {
    }

    // 안됨
//    public <E> void printOtherGeneric(List<E extends Number> e) {
//    }

    // 안됨
//    public <? extends Number> void printOtherGeneric2(List<? extends Number> e) {
//    }

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

        List<Integer> integers = new ArrayList<>();
        integers.add(1);

        List<?> wildCardList = new ArrayList<>();
        wildCardList.add(null);
        // 컴파일 에러
//        wildCardList.add("Hello");
//        wildCardList.add(1);
//        wildCardList.add(true);

        genericTest1.print(strings);

        genericTest1.printWildCardList(strings);
        genericTest1.printWildCardList(integers);
        genericTest1.printWildCardList(wildCardList);

        genericTest1.printNumberList(integers);

        // 컴파일 에러
//        genericTest1.print(wildCardList);
//        genericTest1.print(integers);
//        genericTest1.printNumberList(strings);


    }
}
