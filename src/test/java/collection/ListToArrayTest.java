package collection;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ListToArrayTest {
    private List<String> strings;

    @BeforeEach
    void setUp() {
        strings = List.of("a", "b", "c");
    }

    @DisplayName("크기가 3 이하인 String[]을 이용해서 toArray() 실행")
    @ParameterizedTest(name = "length = {0}")
    @ValueSource(ints = {0, 1, 2, 3})
    void test(int length) {
        // when
        String[] array = strings.toArray(new String[length]);

        // then
        assertThat(array).containsExactly("a", "b", "c");
        assertThat(array.length).isSameAs(3);
    }

    @DisplayName("크기가 3 이상인 String[]을 이용해서 toArray() 실행")
    @ParameterizedTest(name = "length = {0}")
    @ValueSource(ints = {4, 5, 6})
    void test2(int length) {
        // when
        String[] array = strings.toArray(new String[length]);

        // then
        assertThat(array).contains("a", "b", "c");
        assertThat(array).containsNull();
        assertThat(countNull(array)).isEqualTo(length - 3);
    }

    private static long countNull(String[] array) {
        return Arrays.stream(array)
                .filter(Objects::isNull)
                .count();
    }
}
