package loading_vs_init;

public class Main {
    public static void main(String[] args) {
        System.out.println("[ Outer class의 TEST_OUTER 상수만 호출할 때 ]");
        System.out.println(Outer.TEST_OUTER);  // 초기화가 진행되지 않음.
        System.out.println();

        System.out.println("[ Outer class의 getInstance 메서드를 호출할 때 ]");
        Outer instance = Outer.getInstance();  // Outer 클래스의 객체가 생성되면서 초기화 진행.
        System.out.println();

        System.out.println("[ Outer class의 staticMethod() 메서드를 호출할 때 ]");
        System.out.println(Outer.staticMethod());
        System.out.println();

    }
}
