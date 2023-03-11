package innerclass_and_springbean;

import org.springframework.stereotype.Component;

@Component
public class Outer {
    public class Inner {
        public void hello() {
            System.out.println("hello, I'm InnerClass");
        }
    }

    @Component
    class ComponentInner {
        public ComponentInner() {
            System.out.println("hello, I'm ComponentInnerClass");
        }
    }
}
