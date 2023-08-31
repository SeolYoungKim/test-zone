package inherit;

import org.junit.jupiter.api.Test;

public class InheritanceTest {
    private static class Parent {
        public void print() {
            System.out.println(this.getClass() + ": Parent.print()");
        }
    }

    private static class Child extends Parent {
        public void call() {
            System.out.println(this.getClass() + ": Child.call()");
        }
    }

    @Test
    void test() {
        Parent parent = new Parent();
        Parent parentTypeChild = new Child();
        Child child = new Child();

        parent.print();
        parentTypeChild.print();
        child.call();
    }
}
