package strings;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://deveric.tistory.com/123">참고 자료</a>
 */
public class EquivalenceTest {
    @DisplayName("동적으로 생성된 String 객체는 동등성(==) 비교를 하면 false를 반환한다.")
    @Test
    void equivalence1() {
        // given
        List<String> strings = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            strings.add("id" + i);  // 동적으로 할당되는 String 객체
        }

        // when
        String first = strings.get(0);

        // then
        assertThat(first).isNotSameAs("id0");
    }

    @DisplayName("정적으로 생성된 String 객체는 동등성(==) 비교를 하면 true 반환한다.")
    @Test
    void equivalence2() {
        // given
        String firstId = "id0";

        List<String> strings = new ArrayList<>();
        strings.add(firstId);

        for (int i = 0; i < 100; i++) {
            strings.add("id" + (i + 1));
        }

        // when
        String first = strings.get(0);

        // then
        assertThat(first).isSameAs("id0");
    }
}
