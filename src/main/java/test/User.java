package test;

public class User {
    public static void walking() {
        System.out.println("걷는다");
    }

    private final String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;  // 상태
    }
}
