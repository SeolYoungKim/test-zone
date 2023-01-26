package data_structure.list;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SystemArrayCopyTest {

    @DisplayName("복사 대상 어레이의 앞에서부터 5개 요소를 복사하여 앞에서부터 채운다")
    @Test
    void systemArrayCopyLearningTest1() {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] copiedNumbers = new int[10];

        System.arraycopy(numbers, 0, copiedNumbers, 0, 5);
        System.out.println(Arrays.toString(copiedNumbers));
    }

    @DisplayName("복사 대상 어레이의 앞에서부터 5개 요소를 복사하여 6번 인덱스부터 채운다")
    @Test
    void systemArrayCopyLearningTest2() {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] copiedNumbers = new int[10];

        System.arraycopy(numbers, 0, copiedNumbers, 5, 5);
        System.out.println(Arrays.toString(copiedNumbers));
    }

    @DisplayName("복사 대상 어레이의 6번 인덱스부터 5개 요소를 복사하여 앞에서부터 채운다")
    @Test
    void systemArrayCopyLearningTest3() {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] copiedNumbers = new int[10];

        System.arraycopy(numbers, 5, copiedNumbers, 0, 5);
        System.out.println(Arrays.toString(copiedNumbers));
    }

    @DisplayName("복사 대상 어레이의 6번 인덱스부터 2개 요소를 복사하여 앞에서부터 채운다")
    @Test
    void systemArrayCopyLearningTest4() {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] copiedNumbers = new int[10];

        System.arraycopy(numbers, 5, copiedNumbers, 0, 2);
        System.out.println(Arrays.toString(copiedNumbers));
    }

    @DisplayName("삭제 구현")
    @Test
    void systemArrayCopyLearningTest5() {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] copiedNumbers = new int[10];
        int idx = 4;

        System.arraycopy(numbers, 0, copiedNumbers, 0, idx);
        System.arraycopy(numbers, idx + 1, copiedNumbers, idx, numbers.length - idx - 1);
        System.out.println(Arrays.toString(copiedNumbers));
    }
}
