package system;

import java.util.Map;
import java.util.Properties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SystemTest {
    @DisplayName("System에서 제공하는 값들 확인해보기")
    @Test
    void test() {
        Properties properties = System.getProperties();
        System.out.println("properties = " + properties);

        Object javaVersion = properties.get("java.specification.version");
        System.out.println("javaVersion = " + javaVersion);

        Map<String, String> envs = System.getenv();
        System.out.println("PATH = " + envs.get("PATH"));
        System.out.println("JAVA_HOME = " + envs.get("JAVA_HOME"));
    }
}
