package async.future_task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Preloader {
    private final FutureTask<ProductInfo> future = new FutureTask<>(() -> {
        System.out.println("FutureTask 실행. ProductInfo를 미리 로드합니다.");
        final ProductInfo productInfo = loadProductInfo();

        System.out.println("미리 로드한 상품의 정보: " + productInfo.getInfo());
        return productInfo;
    });

    private ProductInfo loadProductInfo() {
        return new ProductInfo();
    }

    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public ProductInfo get() throws ExecutionException, InterruptedException {
        return future.get();
    }

    private static class ProductInfo {
        public String getInfo() {
            return "아주 비싼 상품";
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Preloader 생성");
        final Preloader preloader = new Preloader();

        System.out.println("Preloader에 상품을 미리 로딩합니다.");
        preloader.start();

        Thread.sleep(1000);
        System.out.println("-------------------상품을 사용할 때까지 대기하는 중...-------------------");
        Thread.sleep(5000);

        System.out.println("ProductInfo를 꺼내어 사용합니다.");
        final ProductInfo productInfo = preloader.get();
        System.out.println("사용한 ProductInfo=" + productInfo.getInfo());
    }
}
