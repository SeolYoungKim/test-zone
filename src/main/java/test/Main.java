package test;

public class Main {
    public static void main(String[] args) {
        User h = new User("희도");  // name == 상태
        User d = new User("딱구");
        User water = new User("물");

        System.out.println(h.getName());  // 인스턴스 메서드
        System.out.println(d.getName());
        System.out.println(water.getName());

        // 기능을 추가했다
        User.walking();
        User.walking();
        User.walking();
        User.walking();
    }
}
