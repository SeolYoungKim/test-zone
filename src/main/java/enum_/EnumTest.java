package enum_;

public enum EnumTest {
    A(1), B(2), C(3);

    private final int value;

    EnumTest(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
