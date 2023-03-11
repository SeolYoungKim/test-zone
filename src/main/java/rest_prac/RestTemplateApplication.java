package rest_prac;

import java.util.Map;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class RestTemplateApplication {
    @Bean
    ApplicationRunner init(ErApi erApi) {
        return args -> {
            final RestTemplate rt = new RestTemplate();
            final Map<String, Map<String, Double>> res = rt.getForObject("https://open.er-api.com/v6/latest", Map.class);
            System.out.println(res.get("rates").get("KRW"));

            final WebClient webClient = WebClient.create("https://open.er-api.com");
            final Map<String, Map<String, Double>> block = webClient.get().uri("/v6/latest")
                    .retrieve()
                    .bodyToMono(Map.class)  // 별도의 스레드로 돌아감. netty worker thread -> block
                    .block();
            System.out.println(block.get("rates").get("KRW"));

            // HTTP Interface
            final Map<String, Map<String, Double>> latest = erApi.getLatest();
            System.out.println(latest.get("rates").get("KRW"));
        };
    }

    @Bean
    ErApi erApi() {
        final WebClient webClient = WebClient.create("https://open.er-api.com");
        final HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();

        return httpServiceProxyFactory.createClient(ErApi.class);
    }

    interface ErApi {
        @GetExchange("/v6/latest")
        Map getLatest();
    }

    public static void main(String[] args) {
        SpringApplication.run(RestTemplateApplication.class, args);
    }
}
