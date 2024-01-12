package compile;

public class CompileTest {
    public static void main(String[] args) {
        String str = "Hello World" + "!";

        SomeClass someClass = new SomeClass();
        System.out.println(someClass + "");

        int i = 10;
        System.out.println(i + " is a number");

        Integer integer = null;
        System.out.println(integer + " is a number?");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hello");
        stringBuilder.append(" ");
        stringBuilder.append("World");
        System.out.println(stringBuilder);
    }

    private static class SomeClass {

    }
}
