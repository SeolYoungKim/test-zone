package memory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ObjectMemoryTest {


    @DisplayName("객체의 메모리 크기를 구할 수 있다. - HashMap 테스트")
    @Test
    void memory() {
        long before = MemoryUtils.printMemory("before");

        int objectCount = 3000;
        Map<String, TestClass> testClasses = new HashMap<>();

        for (int i = 0; i < objectCount; i++) {
            String id = String.valueOf(i);
            testClasses.put(id, new TestClass(id));
        }

        MemoryUtils.printMemory("after1");
        long after = MemoryUtils.printMemory("after2");

        System.out.println(objectCount + "개의" + " 객체를 생성했을 때 차지하는 메모리의 양 = " + (after - before) + " bytes");
    }

    static class TestClass {
        private final String id;
        private final String title = "제목제목제제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목목";
        private final String content = "내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용";
        private final String authors = "author1,author2,author3,author4";
        private final String tags = "tag1,tag2,tag3,tag4,tag5,tag6,tag7,tag8,tag9,tag10";
        private final String category = "category";
        private final String thumbnail = "thumbnail";
        private final String url = "url";
        private final LocalDateTime publishedAt = LocalDateTime.now();
        private final LocalDateTime createdAt = LocalDateTime.now();
        private final LocalDateTime updatedAt = LocalDateTime.now();
        private final LocalDateTime deletedAt = LocalDateTime.now();
        private final String status = "status";
        private final String type = "type";
        private final String provider = "provider";
        private final String providerId = "providerId";
        private final String providerAccountId = "providerAccountId";
        private final String providerData = "providerData";

        public TestClass(String id) {
            this.id = id;
        }
    }

    static class TestClass2 extends TestClass {
        public TestClass2(String id) {
            super(id);
        }
    }
}
