package enum_;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class NormalEnumTest {
    @DisplayName("Enum에 추상 메서드가 없으면 각 enum의 class는 같다.")
    @Test
    void classTest() {
        // given
        NormalEnum a = NormalEnum.A;
        NormalEnum b = NormalEnum.B;
        NormalEnum c = NormalEnum.C;

        // when
        Class<? extends NormalEnum> aClass = a.getClass();
        Class<? extends NormalEnum> bClass = b.getClass();
        Class<? extends NormalEnum> cClass = c.getClass();

        // then
        assertEquals(aClass, bClass);
        assertEquals(bClass, cClass);
        assertEquals(cClass, aClass);
    }

    @DisplayName("Enum에 추상 메서드가 없어도 각 enum의 declaringClass는 같다.")
    @Test
    void declaringClassTest() {
        // given
        NormalEnum a = NormalEnum.A;
        NormalEnum b = NormalEnum.B;
        NormalEnum c = NormalEnum.C;

        // when
        Class<? extends NormalEnum> aDeclaringClass = a.getDeclaringClass();
        Class<? extends NormalEnum> bDeclaringClass = b.getDeclaringClass();
        Class<? extends NormalEnum> cDeclaringClass = c.getDeclaringClass();

        // then
        assertEquals(aDeclaringClass, bDeclaringClass);
        assertEquals(bDeclaringClass, cDeclaringClass);
        assertEquals(cDeclaringClass, aDeclaringClass);
    }
}