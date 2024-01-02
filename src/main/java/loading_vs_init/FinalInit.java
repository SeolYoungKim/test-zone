package loading_vs_init;

public class FinalInit {
    private static final int STATIC_A;

    static {
        STATIC_A = 10;
    }

    private final int a;
    
    public FinalInit(int a) {
        this.a = a;
    }
    
    public FinalInit(int a, int b) {
        this.a = a + b;
    }

    void print(final int param) {
        final int local;

        System.out.println("param: " + param);
//        System.out.println("local: " + local);  // 초기화하지 않고 사용하려고 해서 컴파일 에러

        local = param;
        System.out.println("local = " + local);
    }
}
