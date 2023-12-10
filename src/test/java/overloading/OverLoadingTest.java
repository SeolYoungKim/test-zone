package overloading;

public class OverLoadingTest {
    static class TestClass {
        public void testMethod() {
            System.out.println("test");
        }

        public void testMethod(String str) {
            System.out.println(str);
        }

        /*
         * 오버로딩 시 반환 타입은 아무런 영향을 주지 않는다. 매개변수가 큰 영향을 준다.
         *   - 왜일까??
         *     - 반환 타입만 다르다고 해서 컴파일러는 매개변수가 같은 testMethod()를 구분할 재간이 없기 때문이다.
         *     - 컴파일러는 같은 이름의 메서드를, 매개변수의 개수나 타입을 보고 구분한다.
         */
        // 컴파일 에러
//        public String testMethod() {
//            return "test";
//        }
        // 컴파일 에러
//        public String testMethod(String str) {
//            return "test";
//        }

        // 가능
        public String testMethod(String str, int i) {
            return "test";
        }

        public void testMethod(int i, String str) {
            System.out.println("test");
        }
    }
}
