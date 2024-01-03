package enum_;

public class Main {
    public static void main(String[] args) {
        System.out.println(NormalEnum.A.getClass());
        System.out.println(NormalEnum.B.getClass());
        System.out.println(NormalEnum.C.getClass());

        System.out.println("====================");

        System.out.println(NormalEnum.class);
        System.out.println(NormalEnum.A.getDeclaringClass());
        System.out.println(NormalEnum.B.getDeclaringClass());
        System.out.println(NormalEnum.C.getDeclaringClass());

        System.out.println("====================");
        System.out.println(EnumBody.A.getClass());
        System.out.println(EnumBody.B.getClass());
        System.out.println(EnumBody.C.getClass());

        System.out.println("====================");
        System.out.println(EnumBody.class);
        System.out.println(EnumBody.A.getDeclaringClass());
        System.out.println(EnumBody.B.getDeclaringClass());
        System.out.println(EnumBody.C.getDeclaringClass());
    }
}
