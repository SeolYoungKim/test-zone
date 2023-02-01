package final_vs_non_final;

public class Main {
    public static void main(String[] args) {
        final FinalTest finalTest = new FinalTest("final field");
        final C c = new C();
        c.a();
    }

    interface A {
        void a();

        default void b() {
            System.out.println("히히");
        }
    }

    interface B {
        void a();

        default void b() {
            System.out.println("히히");
        }
    }

    static class C implements A, B {

        @Override
        public void a() {
            System.out.println("으아앙");
        }

        @Override
        public void b() {
            System.out.println("이건 재정의를 해야만 함");
        }
    }

    static class D {
        public void d() {
            System.out.println("d");
        }
    }

    static class E {
        public void d() {
            System.out.println("d");
        }
    }
}
