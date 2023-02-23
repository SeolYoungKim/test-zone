package webflux_prac;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;

public class WebFluxApplication {
    public static void main(String[] args) {
        final HttpHandler build = WebHttpHandlerBuilder.applicationContext(
                        new AnnotationConfigApplicationContext()).build();
    }
}
