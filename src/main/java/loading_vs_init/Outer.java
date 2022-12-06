package loading_vs_init;

public class Outer {

    public static final String TEST_OUTER = "Outer Class의 static 상수";

    static {
        System.out.println("Outer 클래스가 초기화 되었습니다.");
    }

    public static String staticMethod() {
        System.out.println("메서드를 호출했습니다.");
        return "뿌애앵";
    }

    public Outer() {
        System.out.println("Outer가 생성되었습니다.");
    }

    public static Outer getInstance() {
        return Inner.INSTANCE;
    }

    private static class Inner {  // Outer class가 초기화 될 때 함께 초기화 된다!!

        private static final Outer INSTANCE = new Outer();  // 해당 객체는 Inner 클래스가 초기화 될 때 생성된다.

        static {
            System.out.println("내부 static 클래스가 초기화 되었습니다.");  // 그래서 지연 로딩이라고 하신듯.
        }
        public Inner() {
            System.out.println("Inner가 생성되었습니다.");
        }

    }

    public class InnerInstanceClass {

        public static final Outer INSTANCE = new Outer();
        static {
            System.out.println("내부 인스턴스 클래스가 초기화 되었습니다.");
        }

    }

    public static void main(String[] args) {
        System.out.println("hi");
    }
}
