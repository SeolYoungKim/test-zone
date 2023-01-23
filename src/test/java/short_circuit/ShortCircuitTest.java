package short_circuit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ShortCircuitTest {
    private boolean trueResult = false;
    private boolean falseResult = false;
    private boolean left = false;
    private boolean right = false;

    @DisplayName("false && true인 경우 true에 해당하는 메서드를 실행하지 않는다.")
    @Test
    void shortCircuitTest1() {
        int a = 0;
        if (onlyFalseResult() && onlyTrueResult()) {
            a++;
        }

        assertThat(a).isEqualTo(0);
        assertThat(trueResult).isFalse();
        assertThat(falseResult).isTrue();
    }

    @DisplayName("!true && !false인 경우 false에 해당하는 메서드를 실행하지 않는다.")
    @Test
    void shortCircuitTest2() {
        int a = 0;
        if (!onlyTrueResult() && !onlyFalseResult()) {
            a++;
        }

        assertThat(a).isEqualTo(0);
        assertThat(trueResult).isTrue();
        assertThat(falseResult).isFalse();
    }

    @DisplayName("true || false인 경우 false에 해당하는 메서드를 실행하지 않는다.")
    @Test
    void shortCircuitTest3() {
        int a = 0;
        if (onlyTrueResult() || onlyFalseResult()) {
            a++;
        }

        assertThat(a).isEqualTo(1);
        assertThat(trueResult).isTrue();
        assertThat(falseResult).isFalse();
    }

    @DisplayName("!(true || false)인 경우 false에 해당하는 메서드를 실행하지 않는다.")
    @Test
    void shortCircuitTest4() {
        int a = 0;
        if (!(onlyTrueResult() || onlyFalseResult())) {
            a++;
        }

        assertThat(a).isEqualTo(0);
        assertThat(trueResult).isTrue();
        assertThat(falseResult).isFalse();
    }

    private boolean onlyTrueResult() {
        System.out.println("onlyTrueResult() 수행");
        trueResult = true;
        return true;
    }

    private boolean onlyFalseResult() {
        System.out.println("onlyFalseResult() 수행");
        falseResult = true;
        return false;
    }

    @DisplayName("삼항 연산자에서 true일 경우 오른쪽 식은 수행되지 않는다.")
    @Test
    void ternaryOperatorTestTrueCase() {
        String result = onlyTrueResult() ? left() : right();

        assertThat(result).isEqualTo("왼쪽 연산식 수행");
        assertThat(left).isTrue();
        assertThat(right).isFalse();
    }

    @DisplayName("삼항 연산자에서 false일 경우 왼쪽 식은 수행되지 않는다.")
    @Test
    void ternaryOperatorTestFalseCase() {
        String result = onlyFalseResult() ? left() : right();

        assertThat(result).isEqualTo("오른쪽 연산식 수행");
        assertThat(left).isFalse();
        assertThat(right).isTrue();
    }

    private String left() {
        System.out.println("왼쪽 연산식 수행");
        left = true;
        return "왼쪽 연산식 수행";
    }

    private String right() {
        System.out.println("오른쪽 연산식 수행");
        right = true;
        return "오른쪽 연산식 수행";
    }
}
