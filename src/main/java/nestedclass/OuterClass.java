package nestedclass;

public class OuterClass {
    private static final int outerStaticField = 10;

    private final int outerField = 10;

    // static nested class는 static이기 때문에 OuterClass 또한 StaticNestedClass의 static 필드만 참조할 수 있다.
    void staticNestedClass() {
        // 당연히 그냥 참조는 안된다. StaticNestedClass가 인스턴스화 되지 않았으니까.
        // System.out.println(staticNestedField);
        System.out.println(StaticNestedClass.staticNestedStaticField);

        StaticNestedClass staticNestedClass = new StaticNestedClass();
        System.out.println(staticNestedClass.staticNestedField);  // 인스턴스화를 해야만 참조가 가능하다~
    }

    void innerClass() {
        System.out.println(InnerClass.innerStaticField);

        // 당연히 그냥 참조는 안된다. InnerClass가 인스턴스화 되지 않았으니까. InnerClass는 OuterClass의 인스턴스화가 필수이므로 참조가 되는 것이다.
//        System.out.println(innerField);
        InnerClass innerClass = new InnerClass();
        System.out.println(innerClass.innerField);  // 인스턴스화를 해야만 참조가 가능하다~
    }

    static class StaticNestedClass {
        private static final int staticNestedStaticField = 10;

        private final int staticNestedField = 10;

        public StaticNestedClass() {
            System.out.println(outerStaticField);
            // System.out.println(outerField);  // outerField는 static이 아니기 때문에 참조할 수 없다.
        }
    }

    class InnerClass {
        private static final int innerStaticField = 10;
        private final int innerField = 10;

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
