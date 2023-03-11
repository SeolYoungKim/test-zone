package innerclass_and_springbean;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class InnerClassApplication {
    @Bean
    public Outer.Inner inner() {
        return new Outer().new Inner();
    }

    @Component
    public class InnerClassApplicationInner {
        public InnerClassApplicationInner() {
            System.out.println("hello, I'm InnerClassApplicationInnerClass");
        }
    }

    @Bean
    ApplicationRunner applicationRunner(Outer.Inner inner) {
        return args -> {
            inner.hello();
        };
    }

    public static void main(String[] args) {
        final ConfigurableApplicationContext ac = SpringApplication.run(
                InnerClassApplication.class, args);

        System.out.println(ac.getBean(Outer.ComponentInner.class));  // 외부 클래스가 빈 등록이 되어야만 빈으로 등록된다.
    }
}
