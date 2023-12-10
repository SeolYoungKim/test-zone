package init;

public class StaticBlockTest {
    static class TestClass {
        static int a;
        int b;

        static {
            a = 10;
            System.out.println("static block");
        }

        {
            b = 10;
            System.out.println("non-static block");
        }

        public TestClass() {
            System.out.println("constructor");
        }

        public static void staticMethod() {
            System.out.println("static method");
        }
    }

    public static void main(String[] args) {
        // static block만 호출 (static 변수를 참조하거나 메서드를 호출할 때 Class가 로딩되고 static block이 실행된다.)
        // System.out.println(TestClass.a);
        // TestClass.staticMethod();

        // static block, non-static block, constructor 호출
        //  static block은 1회 호출, non-static block, constructor는 인스턴스 생성 시마다 호출
        System.out.println("------------------------Construct TestClass------------------------");
        TestClass testClass = new TestClass();

        System.out.println("------------------------Construct TestClass------------------------");
        TestClass testClass2 = new TestClass();

        System.out.println("------------------------Construct TestClass------------------------");
        TestClass testClass3 = new TestClass();
    }
}
