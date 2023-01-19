package loading_vs_init;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OuterTest {

    @DisplayName("Outer class의 TEST_OUTER 상수만 호출할 때")
    @Test
    void constant() {
        System.out.println(Outer.TEST_OUTER);
    }

    @DisplayName("Outer class의 staticMethod() 메서드를 호출할 때")
    @Test
    void staticMethod() {
        System.out.println(Outer.staticMethod());
    }

    @DisplayName("Outer class의 getInstance 메서드를 호출할 때")
    @Test
    void getInstance() {
        System.out.println(Outer.getInstance());
        Assertions.assertEquals(Outer.getInstance(), Outer.getInstance());
        Assertions.assertEquals(Outer.getInstance(), Outer.getInstance());
        Assertions.assertEquals(Outer.getInstance(), Outer.getInstance());
    }
}