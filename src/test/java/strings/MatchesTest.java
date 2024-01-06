package strings;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MatchesTest {
    @DisplayName("regionMatches()를 이용해서 문자열의 특정 부분이 같은지 확인할 수 있다.")
    @Test
    void regionMatchesTrue() {
        // given
        String target = "0123456789";
        String other = "345";

        // when
        boolean result1 = target.regionMatches(3, other, 0, 3);
        boolean result2 = target.regionMatches(4, other, 1, 2);

        // then
        assertThat(result1).isTrue();
        assertThat(result2).isTrue();
    }

    @DisplayName("regionMatches()에서 길이가 다르면 false를 반환한다.")
    @Test
    void regionMatchesFalse() {
        // given
        String target = "0123456789";
        String other = "345";

        // when
        boolean result = target.regionMatches(3, other, 0, 4);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("regionMatches()에서 toOffset과 ooffset의 시작값이 다르면 false를 반환한다.")
    @Test
    void regionMatchesFalse2() {
        // given
        String target = "0123456789";
        String other = "345";

        // when
        boolean result = target.regionMatches(4, other, 0, 3);

        // then
        assertThat(result).isFalse();
    }
}
