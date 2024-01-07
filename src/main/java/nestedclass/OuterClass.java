package nestedclass;

public class OuterClass {
    private static final int outerStaticField = 10;

    private final int outerField = 10;

    static class StaticNestedClass {
        public StaticNestedClass() {
            System.out.println(outerStaticField);
            // System.out.println(outerField);  // outerField는 static이 아니기 때문에 참조할 수 없다.
        }
    }

    class InnerClass {
        public InnerClass() {
            // static nested class와 달리 outerField를 참조할 수 있다.
            // InnerClass는 OuterClass의 인스턴스가 있어야만 생성할 수 있기 때문이다.
            System.out.println(outerStaticField);
            System.out.println(outerField);
        }
    }

    // 인스턴스 메서드는 모든걸 참조할 수 있다. (static, non-static 모두)
    // 그러므로, 인스턴스 메서드 내의 지역 클래스나 익명 클래스 또한 모든걸 참조할 수 있다.
    void method() {
        class LocalClass {
            public LocalClass() {
                System.out.println(outerStaticField);
                System.out.println(outerField);
            }
        }

        Anonymous anonymousClass = new Anonymous() {
            @Override
            public void doSomething() {
                System.out.println(outerStaticField);
                System.out.println(outerField);
            }
        };

        Anonymous anonymousLambda = () -> {
            System.out.println(outerStaticField);
            System.out.println(outerField);
        };
    }
}
