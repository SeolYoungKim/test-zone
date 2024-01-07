package bit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BitTest {
    @DisplayName("Integer의 최솟값을 >> 연산을 이용해 1bit 오른쪽으로 이동하면 왼쪽은 1로 채워진다.")
    @Test
    void bitTest() {
        int minValue = Integer.MIN_VALUE;
        String binaryString = Integer.toBinaryString(minValue);
        System.out.println("binaryString = " + binaryString);

        int shiftRightOne = minValue >> 1;
        String binaryString1 = Integer.toBinaryString(shiftRightOne);
        System.out.println("binaryString1 = " + binaryString1);
        System.out.println("shiftRightOne = " + shiftRightOne);
    }
}
