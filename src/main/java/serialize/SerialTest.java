package serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;
import lombok.Builder;
import lombok.ToString;

public class SerialTest {
    @ToString
    private static class Person implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        private final String JOB = "🐶발자";
        private String name;
        private int age;
        private String address;

        public Person() {
        }

        @Builder
        public Person(String name, int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }
    }
    public static void main(String[] args) {
//        Person person = Person.builder()
//                .name("홍길동")
//                .age(20)
//                .address("서울시")
//                .build();

        String path = "src/main/java/serialize/person.txt";
//        save(path, person);

        Person read = read(path);
        System.out.println("read = " + read);
    }

    private static void save(String path, Person person) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(person);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Person read(String path) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return (Person) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
