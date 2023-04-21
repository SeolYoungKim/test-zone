package dip.before;

public class CalculateDiscountService {
    private final DiscountRuleEngine discountRuleEngine;  // 직접 의존

    public CalculateDiscountService(final DiscountRuleEngine discountRuleEngine) {
        this.discountRuleEngine = discountRuleEngine;
    }

    public void doSomething() {
        // 대충 긴 로직

        discountRuleEngine.evaluate("discountCalculation");  // 직접 의존하기 때문에 변경의 영향을 받음

        // 대충 엄청 긴 로직
    }
}
