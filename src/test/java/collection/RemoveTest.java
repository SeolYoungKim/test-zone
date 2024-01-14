package collection;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RemoveTest {
    private List<String> list = new ArrayList<>();

    @BeforeEach
    void setUp() {
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("a");
    }

    @DisplayName("remove()는 매치되는 첫 번째 요소만 삭제한다")
    @Test
    void test() {
        // when
        list.remove("a");

        // then
        assertThat(list).containsExactly("b", "c", "a");
    }

    @DisplayName("remove()는 매치되는 첫 번째 요소만 삭제한다")
    @Test
    void test2() {
        // when
        list.remove(0);

        // then
        assertThat(list).containsExactly("b", "c", "a");
    }

    @DisplayName("removeAll()은 매치되는 모든 요소를 삭제한다")
    @Test
    void test3() {
        // when
        list.removeAll(List.of("a"));

        // then
        assertThat(list).containsExactly("b", "c");
    }

    @DisplayName("removeIf()는 매치되는 모든 요소를 삭제한다")
    @Test
    void test4() {
        // when
        list.removeIf("a"::equals);

        // then
        assertThat(list).containsExactly("b", "c");
    }
}
