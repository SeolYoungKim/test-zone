package collection;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SetHashCodeEqualsTest {
    @DisplayName("HashCode만 구현된 경우, HashCode가 서로 같아도, Set에서 두 객체는 서로 다른 객체로 취급되어 둘 다 저장된다.")
    @Test
    void onlySameHashCode() {
        // given
        OnlySameHashCode a = new OnlySameHashCode(1);
        OnlySameHashCode b = new OnlySameHashCode(1);

        // when
        Set<OnlySameHashCode> set = new HashSet<>();
        set.add(a);
        set.add(b);

        // then
        assertThat(set).hasSize(2);
        assertThat(set.contains(new OnlySameHashCode(1))).isFalse();  // 의도와는 다르게 동작한다. id가 같아도 equals가 달라 false가 반환된다.
    }

    @DisplayName("HashCode와 Equals가 모두 구현돼야 두 객체가 서로 같은 객체로 취급되어 Set에 하나만 저장된다.")
    @Test
    void sameHashCodeAndEquals() {
        // given
        SameHashCodeAndEquals a = new SameHashCodeAndEquals(1);
        SameHashCodeAndEquals b = new SameHashCodeAndEquals(1);

        // when
        Set<SameHashCodeAndEquals> set = new HashSet<>();
        set.add(a);
        set.add(b);

        // then
        assertThat(set).hasSize(1);
        assertThat(set.contains(new SameHashCodeAndEquals(1))).isTrue();  // 의도대로 동작한다. id가 같고 equals도 같아 true가 반환된다.
    }

    private static class OnlySameHashCode {
        private final int id;

        public OnlySameHashCode(int id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return id;
        }
    }

    private static class SameHashCodeAndEquals {
        private final int id;

        public SameHashCodeAndEquals(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            SameHashCodeAndEquals that = (SameHashCodeAndEquals) o;
            return id == that.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}
