package enum_;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnumBodyTest {
    @DisplayName("Enum에 추상 메서드가 구현되어 있으면 각 enum의 class는 다르다.")
    @Test
    void classTest() {
        // given
        EnumBody a = EnumBody.A;
        EnumBody b = EnumBody.B;
        EnumBody c = EnumBody.C;

        // when
        Class<? extends EnumBody> aClass = a.getClass();
        Class<? extends EnumBody> bClass = b.getClass();
        Class<? extends EnumBody> cClass = c.getClass();

        // then
        assertThat(aClass).isNotSameAs(bClass);
        assertThat(bClass).isNotSameAs(cClass);
        assertThat(cClass).isNotSameAs(aClass);
    }

    @DisplayName("Enum에 추상 메서드가 구현되어 있어도 각 enum의 declaringClass는 같다.")
    @Test
    void declaringClassTest() {
        // given
        EnumBody a = EnumBody.A;
        EnumBody b = EnumBody.B;
        EnumBody c = EnumBody.C;

        // when
        Class<? extends EnumBody> aDeclaringClass = a.getDeclaringClass();
        Class<? extends EnumBody> bDeclaringClass = b.getDeclaringClass();
        Class<? extends EnumBody> cDeclaringClass = c.getDeclaringClass();

        // then
        assertThat(aDeclaringClass).isSameAs(bDeclaringClass);
        assertThat(bDeclaringClass).isSameAs(cDeclaringClass);
        assertThat(cDeclaringClass).isSameAs(aDeclaringClass);
    }
}